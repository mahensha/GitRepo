package com.erevmax;

import static java.lang.System.out;

import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

public class CassanrdaClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final CassandraConnector client = new CassandraConnector();
		final String ipAddress = args.length > 0 ? args[0] : "localhost";
		final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
		out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
		client.connect(ipAddress, port);

		final String createMovieCql = "SELECT cluster_name, listen_address FROM system.local";
		final ResultSet movieResults = client.getSession().execute(createMovieCql);

		List<Row> rows = movieResults.all();

		for (Row row : rows) {
			out.println("=========FROM  DB=====");
			out.println("Cluster_Name:" + row.getString("cluster_name"));
			out.println("listen_address:" + row.getInet("listen_address"));
		}

		client.close();

	}

}
