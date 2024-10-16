import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



class Book {
    private String title;
    private String author;
    private String ISBN;
    private int copies;

    public Book(String title, String author, String ISBN, int copies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getCopies() {
        return copies;
    }

    public void addCopies(int count) {
        this.copies += count;
    }

    public boolean removeCopy() {
        if (this.copies > 0) {
            this.copies--;
            return true;
        }
        return false;
    }

    public void returnCopy() {
        this.copies++;
    }

    @Override
    public String toString() {
        return title + " Автор:" + author + " (ISBN: " + ISBN + "), Копии: " + copies;
    }
}


class Reader
{
    private String name;
    private int id;

    public Reader(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Читатель{" +
                "Имя='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}



class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
        System.out.println("Книга создана: " + book);
    }


    public void removeBook(String ISBN) {
        books.removeIf(book -> book.getISBN().equals(ISBN));
        System.out.println("Книга с  " + ISBN + " удалена.");
    }


    public void registerReader(Reader reader) {
        readers.add(reader);
        System.out.println("Читатель : " + reader + " регистрирована");
    }


    public void removeReader(int id) {
        readers.removeIf(reader -> reader.getId() == id);
        System.out.println("Читатель " + id + " удалена.");
    }


    public void lendBook(String ISBN, int readerId) {
        Book book = findBookByISBN(ISBN);
        if (book != null && book.getCopies() > 0) {
            Reader reader = findReaderById(readerId);
            if (reader != null) {
                if (book.removeCopy()) {
                    System.out.println("Книга'" + book.getTitle() + "' отдался к " + reader.getName());
                } else {
                    System.out.println(" Копии не найдены.");
                }
            } else {
                System.out.println("ЧИтатель не найден.");
            }
        } else {
            System.out.println("Такой книги у нас нету.");
        }
    }


    public void returnBook(String ISBN) {
        Book book = findBookByISBN(ISBN);
        if (book != null) {
            book.returnCopy();
            System.out.println("Книга '" + book.getTitle() + "' возврашена.");
        } else {
            System.out.println("Книга не найдена.");
        }
    }


    private Book findBookByISBN(String ISBN) {
        return books.stream().filter(book -> book.getISBN().equals(ISBN)).findFirst().orElse(null);
    }


    private Reader findReaderById(int id) {
        return readers.stream().filter(reader -> reader.getId() == id).findFirst().orElse(null);
    }


    public void displayBooks() {
        System.out.println("Книга:");
        books.forEach(System.out::println);
    }


    public void displayReaders() {
        System.out.println("Читатали:");
        readers.forEach(System.out::println);
    }
}





public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню библиотеки:");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Удалить книгу");
            System.out.println("3. Зарегистрировать читателя");
            System.out.println("4. Удалить читателя");
            System.out.println("5. Выдать книгу читателю");
            System.out.println("6. Вернуть книгу");
            System.out.println("7. Показать все книги");
            System.out.println("8. Показать всех читателей");
            System.out.println("9. Выйти");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // очистка буфера

            switch (choice) {
                case 1: // Добавить книгу
                    System.out.print("Введите название книги: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите автора книги: ");
                    String author = scanner.nextLine();
                    System.out.print("Введите ISBN книги: ");
                    String ISBN = scanner.nextLine();
                    System.out.print("Введите количество копий книги: ");
                    int copies = scanner.nextInt();
                    library.addBook(new Book(title, author, ISBN, copies));
                    break;

                case 2: // Удалить книгу
                    System.out.print("Введите ISBN книги для удаления: ");
                    String removeISBN = scanner.nextLine();
                    library.removeBook(removeISBN);
                    break;

                case 3: // Зарегистрировать читателя
                    System.out.print("Введите имя читателя: ");
                    String readerName = scanner.next();
                    System.out.print("Введите ID читателя: ");
                    int readerId = scanner.nextInt();
                    library.registerReader(new Reader(readerName, readerId));
                    break;

                case 4: // Удалить читателя
                    System.out.print("Введите ID читателя для удаления: ");
                    int removeReaderId = scanner.nextInt();
                    library.removeReader(removeReaderId);
                    break;

                case 5: // Выдать книгу читателю
                    System.out.print("Введите ISBN книги: ");
                    String lendISBN = scanner.nextLine();
                    System.out.print("Введите ID читателя: ");
                    int lendReaderId = scanner.nextInt();
                    library.lendBook(lendISBN, lendReaderId);
                    break;

                case 6: // Вернуть книгу
                    System.out.print("Введите ISBN книги для возврата: ");
                    String returnISBN = scanner.nextLine();
                    library.returnBook(returnISBN);
                    break;

                case 7: // Показать все книги
                    library.displayBooks();
                    break;

                case 8: // Показать всех читателей
                    library.displayReaders();
                    break;

                case 9: // Выйти
                    System.out.println("Выход из программы.");
                    return;

                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }
}
