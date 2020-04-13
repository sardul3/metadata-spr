package com.sagar.metadatagooglesheet;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;


import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
public class SheetsQuickstart {
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        final String TOKENS_DIRECTORY_PATH = "tokens";
        final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
        final String CREDENTIALS_FILE_PATH = "/credentials.json";
        InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }


    public static void main(String... args) throws IOException, GeneralSecurityException {
         final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
         final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
         final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
         final String spreadsheetId = "1DzRJKs2UOi6Zy7wSkI3YQH215vnhr1-7kCQKR5c5j-k";
         final String range = "Sheet1!A2:CB68";
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            values.forEach(val -> {
                try (FileWriter writer = new FileWriter("data.txt", true)) {


                    val.forEach(v -> {
                        try {
                            writer.write(String.valueOf(v) + ",");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
