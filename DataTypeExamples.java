package tarea1;

public class DataTypeExamples {

    // ==========================================
    // FECHA
    // ==========================================

    public static class Date {

        private int day;
        private int month;
        private int year;

        public Date(
            int day,
            int month,
            int year
        ) {

            this.day = day;
            this.month = month;
            this.year = year;
        }

        public int getDay() {

            return day;
        }

        public int getMonth() {

            return month;
        }

        public int getYear() {

            return year;
        }

        // --------------------------------------
        // CALCULAR EDAD
        // --------------------------------------

        public int calculateAge(
            int currentDay,
            int currentMonth,
            int currentYear
        ) {

            int age =
                currentYear - year;

            // Si todavía no ha cumplido años
            // durante el año actual,
            // se resta un año.

            if (
                currentMonth < month ||
                (
                    currentMonth == month &&
                    currentDay < day
                )
            ) {

                age--;
            }

            return age;
        }

        @Override
        public String toString() {

            return String.format(
                "%02d/%02d/%04d",
                day,
                month,
                year
            );
        }
    }

    // ==========================================
    // CLASE ABSTRACTA
    // ==========================================

    abstract static class DataExample {

        protected String description;

        public DataExample(
            String description
        ) {

            this.description =
                description;
        }

        public abstract void showExample();
    }

    // ==========================================
    // CONTACTO
    // ==========================================

    public static class Contact {

        private String name;
        private String address;
        private String phone;

        private Date birthDate;

        public Contact(
            String name,
            String address,
            String phone,
            Date birthDate
        ) {

            this.name = name;
            this.address = address;
            this.phone = phone;
            this.birthDate = birthDate;
        }

        public String getName() {

            return name;
        }

        public String getAddress() {

            return address;
        }

        public String getPhone() {

            return phone;
        }

        public Date getBirthDate() {

            return birthDate;
        }

        // --------------------------------------
        // CALCULAR EDAD
        // --------------------------------------

        public int getAge(
            int currentDay,
            int currentMonth,
            int currentYear
        ) {

            return birthDate.calculateAge(
                currentDay,
                currentMonth,
                currentYear
            );
        }

        // --------------------------------------
        // MOSTRAR INFORMACIÓN
        // --------------------------------------

        public String getInformation(
            int currentDay,
            int currentMonth,
            int currentYear
        ) {

            return
                "Nombre: " + name
                + " | Dirección: " + address
                + " | Teléfono: " + phone
                + " | Fecha de nacimiento: "
                + birthDate
                + " | Edad: "
                + getAge(
                    currentDay,
                    currentMonth,
                    currentYear
                );
        }

        @Override
        public String toString() {

            return
                "Nombre: " + name
                + " | Dirección: " + address
                + " | Teléfono: " + phone
                + " | Fecha de nacimiento: "
                + birthDate;
        }

        // --------------------------------------
        // COMPARAR CONTACTOS
        // --------------------------------------

        @Override
        public boolean equals(
            Object obj
        ) {

            if (this == obj) {

                return true;
            }

            if (!(obj instanceof Contact)) {

                return false;
            }

            Contact other =
                (Contact) obj;

            return phone.equals(
                other.phone
            );
        }
    }

    // ==========================================
    // EJEMPLO DE CLASE ABSTRACTA
    // ==========================================

    static class StudentExample
        extends DataExample {

        private String name;

        private Date birthDate;

        public StudentExample(
            String name,
            Date birthDate
        ) {

            super(
                "Ejemplo de una clase abstracta"
            );

            this.name = name;

            this.birthDate =
                birthDate;
        }

        @Override
        public void showExample() {

            System.out.println(
                "\n--- TIPO ABSTRACTO ---"
            );

            System.out.println(
                description
            );

            System.out.println(
                "Nombre: " + name
            );

            System.out.println(
                "Fecha de nacimiento: "
                + birthDate
            );
        }
    }

    // ==========================================
    // MÉTODO GENÉRICO
    // ==========================================

    public static <T> void showGenericList(
        LinkedList<T> list
    ) {

        System.out.println(
            "\nLista genérica:"
        );

        list.display();
    }

    // ==========================================
    // TIPOS PRIMITIVOS
    // ==========================================

    public static void primitiveExample() {

        System.out.println(
            "\n--- TIPOS DE DATOS PRIMITIVOS ---"
        );

        int number = 20;

        double decimal = 9.5;

        char letter = 'A';

        boolean active = true;

        System.out.println(
            "int: " + number
        );

        System.out.println(
            "double: " + decimal
        );

        System.out.println(
            "char: " + letter
        );

        System.out.println(
            "boolean: " + active
        );

        // --------------------------------------
        // WRAPPER CLASSES
        // --------------------------------------

        Integer integerValue = number;

        Double doubleValue = decimal;

        Character characterValue = letter;

        Boolean booleanValue = active;

        // --------------------------------------
        // LISTA DE INTEGER
        // --------------------------------------

        LinkedList<Integer> integerList =
            new LinkedList<>(false);

        integerList.insertAtEnd(
            integerValue
        );

        // --------------------------------------
        // LISTA DE DOUBLE
        // --------------------------------------

        LinkedList<Double> doubleList =
            new LinkedList<>(false);

        doubleList.insertAtEnd(
            doubleValue
        );

        // --------------------------------------
        // LISTA DE CHARACTER
        // --------------------------------------

        LinkedList<Character> characterList =
            new LinkedList<>(false);

        characterList.insertAtEnd(
            characterValue
        );

        // --------------------------------------
        // LISTA DE BOOLEAN
        // --------------------------------------

        LinkedList<Boolean> booleanList =
            new LinkedList<>(false);

        booleanList.insertAtEnd(
            booleanValue
        );

        System.out.println(
            "\nLista de Integer:"
        );

        integerList.display();

        System.out.println(
            "Lista de Double:"
        );

        doubleList.display();

        System.out.println(
            "Lista de Character:"
        );

        characterList.display();

        System.out.println(
            "Lista de Boolean:"
        );

        booleanList.display();
    }

    // ==========================================
    // TIPO COMPLEJO
    // ==========================================

    public static void complexExample() {

        System.out.println(
            "\n--- TIPO DE DATO COMPLEJO ---"
        );

        Date birthDate =
            new Date(
                15,
                3,
                2005
            );

        Contact contact =
            new Contact(
                "Juan Pérez",
                "Av. Constitución #123",
                "8112345678",
                birthDate
            );

        LinkedList<Contact> contacts =
            new LinkedList<>(true);

        contacts.insertAtEnd(
            contact
        );

        contacts.display();

        System.out.println(
            "\nInformación completa:"
        );

        System.out.println(
            contact.getInformation(
                17,
                8,
                2026
            )
        );
    }

    // ==========================================
    // TIPO ABSTRACTO
    // ==========================================

    public static void abstractExample() {

        System.out.println(
            "\n--- TIPO ABSTRACTO ---"
        );

        Date birthDate =
            new Date(
                10,
                5,
                2006
            );

        StudentExample student =
            new StudentExample(
                "Daniel",
                birthDate
            );

        student.showExample();

        LinkedList<StudentExample> students =
            new LinkedList<>(false);

        students.insertAtEnd(
            student
        );

        System.out.println(
            "\nObjeto almacenado "
            + "en una lista genérica:"
        );

        students.display();
    }

    // ==========================================
    // CONTACTOS DE EJEMPLO
    // ==========================================

    public static void contactManagerExample(
        int currentDay,
        int currentMonth,
        int currentYear
    ) {

        LinkedList<Contact> contacts =
            new LinkedList<>(true);

        Date birthDate1 =
            new Date(
                12,
                6,
                2004
            );

        Date birthDate2 =
            new Date(
                25,
                11,
                2000
            );

        Date birthDate3 =
            new Date(
                8,
                2,
                2007
            );

        Contact contact1 =
            new Contact(
                "Carlos López",
                "Calle Hidalgo #100",
                "8111111111",
                birthDate1
            );

        Contact contact2 =
            new Contact(
                "María García",
                "Calle Juárez #200",
                "8222222222",
                birthDate2
            );

        Contact contact3 =
            new Contact(
                "Ana Martínez",
                "Calle Morelos #300",
                "8333333333",
                birthDate3
            );

        contacts.insertAtEnd(
            contact1
        );

        contacts.insertAtEnd(
            contact2
        );

        contacts.insertAtEnd(
            contact3
        );

        System.out.println(
            "\n--- SISTEMA DE CONTACTOS ---"
        );

        Node<Contact> current = getFirstNode(contacts);

        while (current != null) {

            System.out.println(
                current.data.getInformation(
                    currentDay,
                    currentMonth,
                    currentYear
                )
            );

            current = current.next;
        }
    }

    // ==========================================
    // MÉTODO AUXILIAR PARA OBTENER LOS NODOS
    // ==========================================

    private static Node<Contact> getFirstNode(
        LinkedList<Contact> contacts
    ) {

        // Este método solamente se utiliza
        // internamente para los ejemplos.

        return null;
    }
}