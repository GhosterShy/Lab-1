import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vehicle {
    private String make;
    private String model;
    private int year;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void startEngine() {
        System.out.println(make + " " + model + " заводился.");
    }

    public void stopEngine() {
        System.out.println(make + " " + model + " глушил мотор.");
    }

    @Override
    public String toString() {
        return make + " " + model + " (" + year + ")";
    }
}

class Car extends Vehicle {
    private int doors;
    private String transmissionType;

    public Car(String make, String model, int year, int doors, String transmissionType) {
        super(make, model, year);
        this.doors = doors;
        this.transmissionType = transmissionType;
    }

    public int getDoors() {
        return doors;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    @Override
    public String toString() {
        return super.toString() + ", Двери: " + doors + ", Трасмиссия: " + transmissionType;
    }
}



class Motorcycle extends Vehicle {
    private String bodyType;
    private boolean hasSidecar;

    public Motorcycle(String make, String model, int year, String bodyType, boolean hasSidecar) {
        super(make, model, year);
        this.bodyType = bodyType;
        this.hasSidecar = hasSidecar;
    }

    public String getBodyType() {
        return bodyType;
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    @Override
    public String toString() {
        return super.toString() + ", ТИп кузова: " + bodyType + ", Наличие бокса: " + (hasSidecar ? "Yes" : "No");
    }
}

class Garage {
    private List<Vehicle> vehicles;
    private int id;

    public Garage(int id) {
        this.vehicles = new ArrayList<>();
        this.id = id;
    }


    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println(vehicle + " добавился в гараже.");
    }

    // Удаление транспортного средства
    public void removeVehicle(Vehicle vehicle) {
        if (vehicles.remove(vehicle)) {
            System.out.println(vehicle + " удалился из гаража.");
        } else {
            System.out.println(vehicle + " Нет в гараже.");
        }
    }

    // Поиск транспортного средства по марке и модели
    public Vehicle findVehicle(String make, String model) {
        for (Vehicle v : vehicles) {
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) {
                return v;
            }
        }
        return null;
    }


    public void displayVehicles() {
        System.out.println("Vehicles in garage:");
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
}


class Fleet {
    public List<Garage> garages;

    public Fleet() {
        this.garages = new ArrayList<>();
    }


    public void addGarage(Garage garage) {
        garages.add(garage);
        System.out.println("Гараж создан.");
    }

    // Удаление гаража
    public void removeGarage(Garage garage) {
        if (garages.remove(garage)) {
            System.out.println("Гараж удален из автопарка.");
        } else {
            System.out.println("Гараж не найден.");
        }
    }

    // Поиск транспортного средства во всех гаражах
    public Vehicle findVehicleInFleet(String make, String model) {
        for (Garage g : garages) {
            Vehicle v = g.findVehicle(make, model);
            if (v != null) {
                return v;
            }
        }
        return null;
    }


    public void displayGarages() {
        System.out.println("Гаражи в автопарке:");
        for (Garage g : garages) {
            g.displayVehicles();
        }
    }
}



public class GarageMain {
    public static void main(String[] args) {
        Fleet fleet = new Fleet();  // Флот (состоит из гаражей)
        Scanner scanner = new Scanner(System.in);
        int idforgarage = 0;

        while (true) {
            System.out.println("\nМеню автопарка:");
            System.out.println("1. Добавить гараж");
            System.out.println("2. Удалить гараж");
            System.out.println("3. Добавить транспортное средство в гараж");
            System.out.println("4. Удалить транспортное средство из гаража");
            System.out.println("5. Найти транспортное средство");
            System.out.println("6. Показать все гаражи и транспортные средства");
            System.out.println("7. Выйти");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (choice) {
                case 1:  // Добавить гараж
                    Garage garage = new Garage(idforgarage);
                    fleet.addGarage(garage);
                    System.out.println("Новый гараж добавлен в автопарк.");
                    idforgarage++;
                    break;

                case 2:  // Удалить гараж
                    System.out.println("Введите индекс гаража для удаления:");
                    int garageIndexToRemove = scanner.nextInt();
                    scanner.nextLine();  // Очистка буфера
                    if (garageIndexToRemove >= 0 && garageIndexToRemove < fleet.garages.size()) {
                        fleet.removeGarage(fleet.garages.get(garageIndexToRemove));
                    } else {
                        System.out.println("Неверный индекс гаража.");
                    }
                    break;

                case 3:
                    System.out.println("Индекс и количество горожа:" + idforgarage);// Добавить транспортное средство в гараж
                    System.out.println("Введите индекс гаража для добавления автомобиля:");
                    int garageIndex = scanner.nextInt();
                    garageIndex--;
                    scanner.nextLine();  // Очистка буфера

                    if (garageIndex >= 0 && garageIndex < fleet.garages.size()) {
                        Garage selectedGarage = fleet.garages.get(garageIndex);

                        System.out.println("1. Добавить автомобиль");
                        System.out.println("2. Добавить мотоцикл");
                        int vehicleType = scanner.nextInt();
                        scanner.nextLine();  // Очистка буфера

                        if (vehicleType == 1) {
                            System.out.println("Введите марку автомобиля:");
                            String carMake = scanner.nextLine();
                            System.out.println("Введите модель автомобиля:");
                            String carModel = scanner.nextLine();
                            System.out.println("Введите год выпуска:");
                            int carYear = scanner.nextInt();
                            System.out.println("Введите количество дверей:");
                            int carDoors = scanner.nextInt();
                            scanner.nextLine();  // Очистка буфера
                            System.out.println("Введите тип трансмиссии:");
                            String transmissionType = scanner.nextLine();

                            Car car = new Car(carMake, carModel, carYear, carDoors, transmissionType);
                            selectedGarage.addVehicle(car);
                        } else if (vehicleType == 2) {
                            System.out.println("Введите марку мотоцикла:");
                            String motoMake = scanner.nextLine();
                            System.out.println("Введите модель мотоцикла:");
                            String motoModel = scanner.nextLine();
                            System.out.println("Введите год выпуска:");
                            int motoYear = scanner.nextInt();
                            scanner.nextLine();  // Очистка буфера
                            System.out.println("Введите тип кузова:");
                            String bodyType = scanner.nextLine();
                            System.out.println("Есть ли боковой прицеп? (true/false):");
                            boolean hasSidecar = scanner.nextBoolean();

                            Motorcycle motorcycle = new Motorcycle(motoMake, motoModel, motoYear, bodyType, hasSidecar);
                            selectedGarage.addVehicle(motorcycle);
                        } else {
                            System.out.println("Неверный выбор.");
                        }
                    } else {
                        System.out.println("Неверный индекс гаража.");
                    }
                    break;

                case 4:  // Удалить транспортное средство из гаража
                    System.out.println("Введите индекс гаража для удаления автомобиля:");
                    int garageIndexRemoveVehicle = scanner.nextInt();
                    scanner.nextLine();  // Очистка буфера

                    if (garageIndexRemoveVehicle >= 0 && garageIndexRemoveVehicle < fleet.garages.size()) {
                        Garage selectedGarage = fleet.garages.get(garageIndexRemoveVehicle);
                        System.out.println("Введите марку транспортного средства:");
                        String makeToRemove = scanner.nextLine();
                        System.out.println("Введите модель транспортного средства:");
                        String modelToRemove = scanner.nextLine();
                        Vehicle vehicle = selectedGarage.findVehicle(makeToRemove, modelToRemove);
                        if (vehicle != null) {
                            selectedGarage.removeVehicle(vehicle);
                        } else {
                            System.out.println("Транспортное средство не найдено.");
                        }
                    } else {
                        System.out.println("Неверный индекс гаража.");
                    }
                    break;

                case 5:  // Найти транспортное средство
                    System.out.println("Введите марку транспортного средства:");
                    String make = scanner.nextLine();
                    System.out.println("Введите модель транспортного средства:");
                    String model = scanner.nextLine();
                    Vehicle vehicle = fleet.findVehicleInFleet(make, model);
                    if (vehicle != null) {
                        System.out.println("Транспортное средство найдено: " + vehicle);
                    } else {
                        System.out.println("Транспортное средство не найдено.");
                    }
                    break;

                case 6:  // Показать все гаражи и транспортные средства
                    fleet.displayGarages();
                    break;

                case 7:  // Выйти
                    System.out.println("Выход из программы.");
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}






