package menu.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GoogleParser {

    public static Map<String, String> client_list = new HashMap<>();

    public static Map<String, String> client_name_list = new HashMap<>();

    public static Map<String, String> count_list = new HashMap<>();

    private static final String PUBLIC_URL = "https://docs.google.com/spreadsheets/d/15Y82ZbnhC2PJzgbizKTRIFgszu0BKP7JeG4MPz0_6oc/edit?resourcekey=&gid=168282432#gid=168282432"; // Замените на URL вашей опубликованной таблицы

    private static final String PUBLIC_URL_2 = "https://docs.google.com/spreadsheets/d/15Y82ZbnhC2PJzgbizKTRIFgszu0BKP7JeG4MPz0_6oc/edit?resourcekey=&gid=264780297#gid=264780297";

    public static void goPars() {
        try {
            Document doc = Jsoup.connect(PUBLIC_URL).get();

            Element table = doc.select("table").first();

            if (table != null) {

                Elements rows = table.select("tr");

                int startCol = 3;
                int endCol = 4 + 1;

                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td, th");

                    boolean isEmptyRow = true;
                    StringBuilder rowText = new StringBuilder();

                    for (int j = startCol; j < endCol && j < cols.size(); j++) {
                        Element col = cols.get(j);
                        String text = col.text().trim();
                        rowText.append(text).append("\t");

                        if (!text.isEmpty()) {
                            isEmptyRow = false;
                        }
                    }


                    if (!isEmptyRow) {
                        String[] lines = rowText.toString().trim().split("\t");
                        if (lines.length >= 2) {
                            String key = lines[0];
                            String value = lines[1];
                            client_list.put(key, value);
                        }
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("Table not found in the provided URL.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void goParsName() {
        try {
            Document doc = Jsoup.connect(PUBLIC_URL).get();

            Element table = doc.select("table").first();

            if (table != null) {

                Elements rows = table.select("tr");

                int startCol = 2;
                int endCol = 3 + 1;

                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td, th");

                    boolean isEmptyRow = true;
                    StringBuilder rowText = new StringBuilder();

                    for (int j = startCol; j < endCol && j < cols.size(); j++) {
                        Element col = cols.get(j);
                        String text = col.text().trim();
                        rowText.append(text).append("\t");

                        if (!text.isEmpty()) {
                            isEmptyRow = false;
                        }
                    }

                    if (!isEmptyRow) {
                        String[] lines = rowText.toString().trim().split("\t");
                        if (lines.length >= 2) {
                            String key = lines[0];
                            String value = lines[1];
                            client_name_list.put(key, value);
                        }
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("Table not found in the provided URL.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void goParsCount() {
        try {

            Document doc = Jsoup.connect(PUBLIC_URL_2).get();
            Element table = doc.select("table").first();

            if (table != null) {

                Elements rows = table.select("tr");

                int startCol = 1;
                int endCol = 2 + 1;

                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td, th");

                    boolean isEmptyRow = true;
                    StringBuilder rowText = new StringBuilder();

                    for (int j = startCol; j < endCol && j < cols.size(); j++) {
                        Element col = cols.get(j);
                        String text = col.text().trim();
                        rowText.append(text).append("\t");

                        if (!text.isEmpty()) {
                            isEmptyRow = false;
                        }
                    }


                    if (!isEmptyRow) {
                        String[] lines = rowText.toString().trim().split("\t");
                        if (lines.length >= 2) {
                            String key = lines[0];
                            String value = lines[1];
                            count_list.put(key, value);
                        }
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("Table not found in the provided URL.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String checkAuth(String username) {
        String result = "Не пройдена";
        for (Map.Entry<String, String> entry : client_list.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            //System.out.println(key+":"+value+"\n");
            if (key.equals(username)) {
                result = value;
                break;
            }
        }
        return result;
    }

    public static String checkName(String username) {
        String result = "Не авторизован";
        for (Map.Entry<String, String> entry : client_name_list.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            //System.out.println(key+":"+value+"\n");
            if (value.equals(username)) {
                result = key;
                break;
            }
        }
        return result;
    }

    public static Integer sendCount(String type){
        int count = 0;
        for (Map.Entry<String, String> entry : count_list.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            //System.out.println(key+":"+value+"\n");
            if (key.equals(type)) {
                count = Integer.parseInt(value);
                break;
            }
        }
        return count;
    }


}
