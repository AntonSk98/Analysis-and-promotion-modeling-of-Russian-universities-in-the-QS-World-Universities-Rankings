import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from scipy.stats import pearsonr
import matplotlib.patches as mpatches

general_path_to_save = 'D:\\all_stuff\\диплом2019\\thesis\\images\\second chapter\\'

qs_university_rankings_criteria = ['Academic Reputation', 'Employer Reputation',
                                   'Faculty Student', 'Citations per Faculty', 'International Faculty',
                                   'International Students', 'Overall Score']


def read_excel_file(*columns_to_read):
    path_to_qs_file = 'D:\\all_stuff\\диплом2019\\thesis''\\QS_world_university_rankings_2020.xlsx'
    qs_university_ranking_overview = pd.read_excel(path_to_qs_file, nrows=500, usecols=columns_to_read).fillna(0)
    return qs_university_ranking_overview


def get_polynomial_data(x_values, y_values):
    x = []
    y = []
    polynomial_coefficients = np.polyfit(x_values, y_values, 4)
    equation = 'C1*x^4+C2*x^3+C3*x^2\n+C4*x+Intercept'
    for x_value in x_values:
        x.append(x_value)
        y.append(polynomial_coefficients[0] * pow(x_value, 4) + polynomial_coefficients[1] * pow(x_value, 3)
                 + polynomial_coefficients[2] * pow(x_value, 2) + polynomial_coefficients[3] * x_value
                 + polynomial_coefficients[4])
    r_squared = np.sum((np.poly1d(polynomial_coefficients)(x) - np.sum(y_values)
                        / len(y_values)) ** 2) / np.sum((y_values - np.sum(y_values) / len(y_values)) ** 2)
    return x, y, equation, polynomial_coefficients, r_squared


def correlation_btw_parameter_and_uni_ranking(excel_file, column_name, xlabel):
    data_for_analysis = {'uni_ranking': list(range(0, 500)),
                         'parameter': sorted(list(excel_file[column_name]), reverse=True)}
    x_polynomials, y_polynomials, equation, coefficients, r_squared = get_polynomial_data(
        list(data_for_analysis['uni_ranking']), list(data_for_analysis['parameter']))
    plt.scatter(data_for_analysis['uni_ranking'], data_for_analysis['parameter'], s=15, marker='s', label='Overall')
    plt.plot(x_polynomials, y_polynomials, color='red', linewidth=2, label='Polynomial Fit')
    plt.title('{} correlation'.format(column_name))
    plt.xlabel(xlabel)
    plt.ylabel(column_name)
    plt.grid()
    plt.legend()
    plt.text(550, 60, 'equation:\n\nC1:\nC2:\nC3:\nC4:\nIntercept:\nR^2:', fontsize='small',
             bbox={'edgecolor': 'black', 'facecolor': 'white'})
    plt.text(623, 60, '{}\n{}\n{}\n{}\n{}\n{}\n{}'
             .format(equation, round(coefficients[0], 10), round(coefficients[1], 8),
                     round(coefficients[2], 5), round(coefficients[3], 5),
                     round(coefficients[4], 5), round(r_squared, 5)), fontsize='small',
             bbox={'edgecolor': 'black', 'facecolor': 'white'})
    figure = plt.gcf()
    plt.savefig(general_path_to_save+'{}.png'.format(column_name), bbox_inches='tight', )
    plt.close(figure)


def correlation_pearson_analysis(excel_file, file_name):
    pearson_correlation_data = excel_file.corr()
    plt.title('Correlation heatmap of QS criteria\n')
    correlation_map = sns.heatmap(pearson_correlation_data, linewidths=2, annot=True)
    correlation_map.get_figure().savefig(general_path_to_save+'{}.png'.format(file_name), bbox_inches='tight', )
    plt.close(plt.gcf())


def get_general_statistical_data(excel_file):
    row_data = []
    column_data = []
    table_data = [[] for _ in range(8)]
    statistical_data = excel_file.describe()
    for index, column in enumerate(statistical_data):
        column_data.append(column)
        if index == 0:
            row_data = list(statistical_data[column].keys())
        for index2, value in enumerate(statistical_data[column]):
            table_data[index2].append(round(value, 2))
    return column_data, row_data, table_data


def get_general_statistical_data_table(excel_file):
    column_data, row_data, table_data = get_general_statistical_data(excel_file)
    plt.figure(figsize=(9, 3))
    plt.table(cellText=table_data,
              rowLabels=row_data,
              colLabels=column_data, loc='center', colWidths=[0.25 for _ in column_data])
    plt.axis('off')
    plt.title('Statistical data of summary QS criteria\n')
    plt.savefig(general_path_to_save+'Statistical data of summary QS criteria.png', bbox_inches='tight', )
    plt.close(plt.gcf())


def pearson_p_val(x, y):
    return pearsonr(x, y)[1]


def correlation_pearson_p_values(excel_file, file_name):
    corr = excel_file.corr(method=pearson_p_val)
    correlation_map = sns.heatmap(corr, linewidths=2, annot=True, center=2, cbar=False)
    plt.title('Correlation p-values of QS criteria\n')
    correlation_map.get_figure().savefig(general_path_to_save+'{}.png'.format(file_name), bbox_inches='tight', )
    plt.close(plt.gcf())


def polynomial_regression_analysis(excel_file):
    overall_score = 'Overall Score'
    criteria = qs_university_rankings_criteria.copy()
    criteria.remove(overall_score)
    y_values = excel_file[overall_score]
    for criterion in criteria:
        sns.regplot(x=criterion, y=overall_score, data=excel_file, fit_reg=False, color='blue', label='{} distribution'
                    .format(criterion))
        cf = np.polyfit(excel_file[criterion], excel_file[overall_score], 2)
        r_squared = np.sum((np.poly1d(cf)(excel_file[criterion]) - np.sum(y_values) / len(y_values)) ** 2) \
                    / np.sum((y_values - np.sum(y_values) / len(y_values)) ** 2)
        x = np.linspace(0, 100)
        y = cf[0]*x**2+cf[1]*x+cf[2]
        plt.plot(x, y, color='black', linewidth=3, label='Polynomial Fit')
        plt.plot([], [], label='R-squared: {}'.format(round(r_squared, 5)), color='none')
        plt.legend()
        plt.savefig(general_path_to_save+'{}_regression.png'.format(criterion), bbox_inches='tight',)
        plt.close(plt.gcf())


def get_trendline_of_russian_unis_in_qs(excel_file):
    overall_score = 'Overall Score'
    qs_top_500_russian_unis = excel_file[excel_file['Country'] == 'Russia']
    criteria = qs_university_rankings_criteria.copy()
    criteria.remove(overall_score)
    plt.figure(figsize=(15,12))
    for index, criterion in enumerate(criteria):
        plt.subplot(2,3,index+1)
        plt.title('{} trendline'.format(criterion))
        sns.regplot(x='Year 2020', y=criterion, data=qs_top_500_russian_unis,
                    label='{} value'.format(criterion), scatter=True, color='blue')
        plt.xlabel('University position in QS 2020')
        plt.legend()
    plt.savefig(general_path_to_save+'trendlines.png', bbox_inches='tight',)