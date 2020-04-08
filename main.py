import sys
from statistical_analyzer import *

excelFile = read_excel_file('Year 2020', 'Institution Name', 'Country', *qs_university_rankings_criteria)

for criterion in qs_university_rankings_criteria:
    correlation_btw_parameter_and_uni_ranking(excelFile, criterion, 'University ranking')

correlation_pearson_analysis(excelFile[qs_university_rankings_criteria], 'person_analysis')
get_general_statistical_data_table(excelFile[qs_university_rankings_criteria])
correlation_pearson_p_values(excelFile[qs_university_rankings_criteria], 'correlation_analysis_p-values')


polynomial_regression_analysis(excelFile[qs_university_rankings_criteria])
get_trendline_of_russian_unis_in_qs(excelFile)
sys.exit()

# excelFile[qs_university_rankings_criteria].hist() do i need it??
# pyplot.show()