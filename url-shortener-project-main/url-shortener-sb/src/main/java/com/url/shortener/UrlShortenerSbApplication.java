package com.url.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class UrlShortenerSbApplication {

	public static void main(String[] args) {
		String databaseUrl = System.getenv("DATABASE_URL");
		if (databaseUrl != null && (databaseUrl.startsWith("postgres://") || databaseUrl.startsWith("postgresql://"))) {
			try {
				URI dbUri = new URI(databaseUrl);
				String userInfo = dbUri.getUserInfo();
				if (userInfo != null && userInfo.contains(":")) {
					String[] parts = userInfo.split(":", 2);
					String username = URLDecoder.decode(parts[0], StandardCharsets.UTF_8);
					String password = URLDecoder.decode(parts[1], StandardCharsets.UTF_8);
					
					String host = dbUri.getHost();
					int port = dbUri.getPort();
					String path = dbUri.getPath();
					String portStr = (port == -1) ? "" : ":" + port;
					String dbUrl = "jdbc:postgresql://" + host + portStr + path;
					
					System.setProperty("spring.datasource.url", dbUrl);
					System.setProperty("spring.datasource.username", username);
					System.setProperty("spring.datasource.password", password);
				}
			} catch (URISyntaxException e) {
				System.err.println("Failed to parse DATABASE_URL: " + e.getMessage());
			}
		}
		SpringApplication.run(UrlShortenerSbApplication.class, args);
	}

}
