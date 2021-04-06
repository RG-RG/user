package kr.co.rgrg.user.mypage.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.model.ColumnHeader;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.DateRangeValues;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.DimensionFilter;
import com.google.api.services.analyticsreporting.v4.model.DimensionFilterClause;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.MetricHeaderEntry;
import com.google.api.services.analyticsreporting.v4.model.Report;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;
import com.google.api.services.analyticsreporting.v4.model.ReportRow;

public class HelloAnalytics {
	private static final String APPLICATION_NAME = "Hello Analytics Reporting";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	static String path = HelloAnalytics.class.getResource("").getPath();
	private static final String KEY_FILE_LOCATION = path + "../../../../../../../../key/co-doing-05efd00e575b.json";
	private static final String VIEW_ID = "239719938";
//	@Value("${google.analytics.key")
//	private static String KEY_FILE_LOCATION;
	
//	@Value("${google.analytics.viewid}")
//	private static String VIEW_ID;

	public static String getResult(String id, String startDate, String endDate, String metrics, String dimension) {
		try {
			System.out.println(VIEW_ID + " " + KEY_FILE_LOCATION);
			AnalyticsReporting service = initializeAnalyticsReporting();

			GetReportsResponse response = getReport(service, startDate, endDate, id, metrics, dimension);
			printResponse(id, response);
			return response.toPrettyString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	/**
	 * Initializes an Analytics Reporting API V4 service object.
	 *
	 * @return An authorized Analytics Reporting API V4 service object.
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	private static AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {

		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(KEY_FILE_LOCATION))
				.createScoped(AnalyticsReportingScopes.all());

		// Construct the Analytics Reporting service object.
		return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	/**
	 * Queries the Analytics Reporting API V4.
	 *
	 * @param service An authorized Analytics Reporting API V4 service object.
	 * @return GetReportResponse The Analytics Reporting API V4 response.
	 * @throws IOException
	 */
	private static GetReportsResponse getReport(AnalyticsReporting service, String startDate, String endDate, String id, String metrics, String dimension) throws IOException {
		// Create the DateRange object.
		DateRange dateRange = new DateRange();
		dateRange.setStartDate("7daysAgo");
		dateRange.setEndDate("today");
		
		List<DateRange> dateRanges = new ArrayList<DateRange>();
		dateRanges.add(dateRange);

		// Create the Metrics object.

		Dimension reqDimension = new Dimension().setName("ga:" + dimension);
		Dimension date = new Dimension().setName("ga:date");
		
		Dimension device = new Dimension().setName("ga:deviceCategory");
		
		Metric reqMetrics = new Metric().setExpression("ga:" + metrics);
		Metric sessions = new Metric().setExpression("ga:sessions").setAlias("sessions");

		DimensionFilter filters = new DimensionFilter().setDimensionName("ga:pagePath").setExpressions(Arrays.asList("^/" + id + "/"));
		DimensionFilterClause filter = new DimensionFilterClause().setFilters(Arrays.asList(filters));
		
		
		
		
		// Create the ReportRequest object.
		ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
		
		ReportRequest dataRequest = new ReportRequest().setViewId(VIEW_ID).setDateRanges(Arrays.asList(dateRange))
				.setMetrics(Arrays.asList(reqMetrics))
				.setDimensions(Arrays.asList(reqDimension)).setDimensionFilterClauses(Arrays.asList(filter)).setIncludeEmptyRows(true);
		requests.add(dataRequest);
		
		
		// Create the GetReportsRequest object.
		GetReportsRequest getReport = new GetReportsRequest().setReportRequests(requests);

		// Call the batchGet method.
		GetReportsResponse response = service.reports().batchGet(getReport).execute();

		// Return the response.
		return response;
	}

	/**
	 * Parses and prints the Analytics Reporting API V4 response.
	 *
	 * @param response An Analytics Reporting API V4 response.
	 */
	private static void printResponse(String id, GetReportsResponse response) {

		for (Report report : response.getReports()) {
			ColumnHeader header = report.getColumnHeader();
			List<String> dimensionHeaders = header.getDimensions();
			List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
			List<ReportRow> rows = report.getData().getRows();

			if (rows == null) {
				System.out.println("No data found for " + VIEW_ID);
				return;
			}

			for (ReportRow row : rows) {
				List<String> dimensions = row.getDimensions();
				List<DateRangeValues> metrics = row.getMetrics();

				for (int i = 0; i < dimensionHeaders.size() && i < dimensions.size(); i++) {
					System.out.println(dimensionHeaders.get(i) + ": " + dimensions.get(i));
				}

				for (int j = 0; j < metrics.size(); j++) {
					System.out.print("Date Range (" + j + "): ");
					DateRangeValues values = metrics.get(j);
					for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
						System.out.println(metricHeaders.get(k).getName() + ": " + values.getValues().get(k));
					}
				}
			}
		}
	}
}
