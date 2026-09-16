import java.util.*;
class TestJava {
    private int id;
    private String name;
    private double salary;
    public TestJava(int id, String name, double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

        public int getId(){
            return id;
        }
        public void setId(int id){
            this.id = id;
        }
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public double getSalary(){
            return salary;
        }
        public void setSalary(double salary){
            this.salary = salary;
        }
        public void display(){
            System.out.println("Id: "+ id);
            System.out.println("Name: "+ name);
            System.out.println("Salary: "+ salary);
        }

        private static Employee findemployee(ArrayList<Employee> employees, int id){
            for (Employee emp : employees){
                if (emp.getId() == id){
                    return emp;
                }
            }
            return null;
        }

    private static void addEmployee(ArrayList<Employee> employees, Scanner sc){
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        if (findemployee(employees, id) != null){
            System.out.println("Error: Employee with ID " + id + "already exists.");
            return;
        }
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee added Successfully!");
    }
    private static void updateEmployee(ArrayList<Employee> employees, Scanner sc){
        System.out.print("Enter Employee Id to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Employee emp = findemployee(employees, id);
        if (emp == null){
            System.out.println("Error: Employee with ID" + id + "not found.");
            return;
        }
        System.out.print("Enter New Employee Name: ");
        String name = sc.nextLine();
        System.out.print("Enter New Employee Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        emp.setName(name);
        emp.setSalary(salary);
        System.out.println("Employee details updated successfully!");
    }
    private static void deleteEmployee(ArrayList<Employee> employees, Scanner sc){
        System.out.print("Enter Employee Id to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        Employee emp = findemployee(employees, id);
        if (emp == null){
            System.out.println("Error: Employee with ID" + id + "not found.");
            return;
        }
        employees.remove(emp);
        System.out.println("Employee deleted successfully!");
    }
    private static void searchemployee(ArrayList<Employee> employees, Scanner sc){
        System.out.print ("Enter Employee Id to searcch: ");
        int id = sc.nextInt();
        sc.nextLine();
        Employee emp = findemployee(employees, id);
        if (emp == null){
            System.out.println("Error: Employee with ID" + id + "not found");
        }else{
            System.out.println("\n--- Employee Found ---");
            emp.display();
        }
    }
    private static void displayAllEmployees(ArrayList<Employee> employees){
        if(employees.isEmpty()){
            System.out.println("No employees found in the list.");
            return;
        }
        System.out.println("\n--- All Employees ---");
        for (Employee emp : employees){
            emp.display();
            System.out.println("--------------------");
        }
    }
    public static void main(String[] args){
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        
        do {
            System.out.println("\n --- Employee Management System ---\n");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            if(!sc.hasNextInt()){
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            switch (choice){
                case 1: 
                addEmployee(employees, sc);
                break;
                case 2:
                    updateEmployee(employees, sc);
                    break;
                case 3:
                    deleteEmployee(employees, sc);
                    break;
                case 4:
                    searchemployee(employees,sc);
                    break;
                case 5:
                    displayAllEmployees(employees);
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                }
          }while(choice != 6);
        sc.close();
        }
    }
