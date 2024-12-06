import java.io.*;
import java.util.*;

public class Candidate {
    String gender;
    String regNumber;
    String regDate;
    String details;
    String requirements;

    private static  String DATABASE_FILE = "Candidate.txt";
    private static  List<Candidate> candidates = new ArrayList<>();

    public Candidate(String gender, String regNumber, String regDate, String details, String requirements) {
        this.gender = gender;
        this.regNumber = regNumber;
        this.regDate = regDate;
        this.details = details;
        this.requirements = requirements;
    }

    // Метод для создания файла Candidate.txt, если он не существует
    public static void createDatabaseFile() {
        File file = new File(DATABASE_FILE);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Файл Candidate.txt создан.");
                } else {
                    System.err.println("Ошибка при создании файла Candidate.txt.");
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

    //Метод для загрузки данных из файла Candidate.txt
    public static void loadCandidates() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DATABASE_FILE));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    try {
                        Candidate candidate = new Candidate(parts[0], parts[1], parts[2], parts[3], parts[4]);
                        candidates.add(candidate);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }


            }
            System.out.println("Данные загружены");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            throw new RuntimeException(e);
        }
    }

    //Метод для сохранения данных в файл Candidate.txt
    public static void saveCandidates() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE_FILE));
            for (Candidate candidate : candidates) {
                bw.write(candidate.gender + "," + candidate.regNumber + "," + candidate.regDate + "," + candidate.details + "," + candidate.requirements);
                bw.newLine();
            }
            bw.close();
            System.out.println("Данные сохранены");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
            throw new RuntimeException(e);
        }
    }

    //Метод для добавления данных в файл Candidate.txt
    public static void addCandidate(Candidate candidate) {
        candidates.add(candidate);
        saveCandidates();
        System.out.println("Данные добавлены");
    }

    //Метод для удаления данных из файла Candidate.txt
    public static void deleteCandidate(String id) {
        candidates.removeIf(candidate -> candidate.regNumber.equals(id));
        System.out.println("Кандидат удален.");
    }

    //Метод для просмотра данных из файла Candidate.txt
    public static void viewCandidates() {
        if (candidates.isEmpty()) {
            System.out.println("База данных пуста");
        }
        else {
            System.out.println("База данных кандидатов: ");
            for (Candidate candidate : candidates) {
               System.out.println("Пол: " + candidate.gender + " Рег. номер: " + candidate.regNumber + " Дата регистрации: " + candidate.regDate + " Детали: " + candidate.details + " Требования: " + candidate.requirements);
            }
        }
    }
}


