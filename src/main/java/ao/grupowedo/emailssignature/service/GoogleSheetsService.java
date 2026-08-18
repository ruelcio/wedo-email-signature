package ao.grupowedo.emailssignature.service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import ao.grupowedo.emailssignature.model.Employee;

@Service
public class GoogleSheetsService {

    private static final String APPLICATION_NAME = "Wedo Email Signature";
    private static final String RANGE = "Colaboradores!A:E";

    public List<Employee> readEmployees() throws Exception {

        String spreadsheetId = System.getenv("GOOGLE_SHEETS_ID");
        String credentialsJson = System.getenv("GOOGLE_CREDENTIALS_JSON");

        if (spreadsheetId == null || spreadsheetId.isBlank()) {
            throw new IllegalStateException(
                "A variável GOOGLE_SHEETS_ID não está configurada."
            );
        }

        if (credentialsJson == null || credentialsJson.isBlank()) {
            throw new IllegalStateException(
                "A variável GOOGLE_CREDENTIALS_JSON não está configurada."
            );
        }

        GoogleCredentials credentials = GoogleCredentials
            .fromStream(
                new ByteArrayInputStream(
                    credentialsJson.getBytes(StandardCharsets.UTF_8)
                )
            )
            .createScoped(
                Collections.singleton(
                    "https://www.googleapis.com/auth/spreadsheets.readonly"
                )
            );

        Sheets sheetsService = new Sheets.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(),
            new HttpCredentialsAdapter(credentials)
        )
            .setApplicationName(APPLICATION_NAME)
            .build();

        ValueRange response = sheetsService
            .spreadsheets()
            .values()
            .get(spreadsheetId, RANGE)
            .execute();

        List<Employee> employees = new ArrayList<>();

        if (response.getValues() == null) {
            return employees;
        }

        List<List<Object>> rows = response.getValues();

        for (int i = 1; i < rows.size(); i++) {

            List<Object> row = rows.get(i);

            Long id = row.size() > 0
                ? Long.parseLong(row.get(0).toString())
                : null;

            String name = row.size() > 1
                ? row.get(1).toString()
                : "";

            String position = row.size() > 2
                ? row.get(2).toString()
                : "";

            String email = row.size() > 3
                ? row.get(3).toString()
                : "";

            String phone = row.size() > 4
                ? row.get(4).toString()
                : "";

            Employee employee = new Employee(
                id,
                name,
                position,
                email,
                phone
            );

            employees.add(employee);
        }
        return employees;
    }
}