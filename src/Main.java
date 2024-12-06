import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean work = true;
        try {
            Candidate.loadCandidates();
            while (work) {
                System.out.println("\n --- Выберите действие ---");
                System.out.println("1. Добавить кандидата");
                System.out.println("2. Просмотреть всех кандидатотв");
                System.out.println("3.Удалить кандидата");
                System.out.println("4. Выход");
                int choice = scan.nextInt();
                scan.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Введите пол кандидата(м/ж): ");
                        String gender= scan.nextLine();

                        System.out.print("Введите регистрационный номер: ");
                        String regNumber = scan.nextLine();

                        System.out.print("Введите дату регистрации (ГГГГ-ММ-ДД): ");
                        String regDate = scan.nextLine();

                        System.out.print("Введите сведения о себе: ");
                        String details = scan.nextLine();

                        System.out.print("Введите требования к партнеру: ");
                        String requirements = scan.nextLine();
                        Candidate candidate = new Candidate(gender, regNumber, regDate, details, requirements);
                        Candidate.addCandidate(candidate);
                        break;

                    case 2:
                        Candidate.viewCandidates();
                        break;

                        case 3:
                            System.out.println("Введите номер кандидата: ");
                            String idToDelete = scan.nextLine();
                            Candidate.deleteCandidate(idToDelete);
                            break;

                    case 4:
                        work = false;
                        Candidate.saveCandidates();
                        System.out.println("Выход");
                        break;
                }

            }
            } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}