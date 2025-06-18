## Meniuri si citiri

### 1. Citire de la tastatură (numar, string, boolean)
```java
Scanner scanner = new Scanner(System.in);
System.out.println("Introduceți un număr: ");
int numar = scanner.nextInt();
System.out.println("Numărul introdus este: " + numar);
```
```java
System.out.print("Introduceți un text: ");
String text = scanner.next();
System.out.println("Textul introdus este: " + text);
```
```java
System.out.print("Introduceți 'da' sau 'nu': ");
String textIntrodus = scanner.next();
boolean booleanValoare = textIntrodus.equalsIgnoreCase("da");
System.out.println("Valoarea booleană este: " + booleanValoare);
```

### 2. Meniu infinit
```java
Scanner scanner = new Scanner(System.in);
int optiune;

while (true) {
    System.out.println("\n--- Meniu ---");
    System.out.println("1. Opțiunea 1");
    System.out.println("2. Opțiunea 2");
    System.out.println("0. Ieșire");
    System.out.print("Alegeți o opțiune: ");

    optiune = scanner.nextInt();

    switch (optiune) {
        case 1:
            System.out.println("Ați ales Opțiunea 1.");
            // Adaugă logica pentru opțiunea 1
            break;
        case 2:
            System.out.println("Ați ales Opțiunea 2.");
            // Adaugă logica pentru opțiunea 2
            break;
        case 0:
            System.out.println("Programul se închide. La revedere!");
            scanner.close();
            return; // sau break din while dacă ești într-o metodă care nu e main
        default:
            System.out.println("Opțiune invalidă. Vă rugăm să reîncercați.");
            break;
    }
}
```

### 3. Citire din fișier
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

FileReader fileReader = new FileReader("cale/catre/fișier.txt");
BufferedReader bufferedReader = new BufferedReader(fileReader);
ArrayList<String> linii = new ArrayList<>();
String linie;
while ((linie = bufferedReader.readLine()) != null) {
    linii.add(linie);
}
bufferedReader.close();
```
### 4.Impartire linie in cuvinte
```java
import java.util.ArrayList;
String linie = "Aceasta este o linie de text.";
String[] cuvinte = linie.split(" "); // Impartire in cuvinte
```

### 5. Aduga linie in fișier
```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
FileWriter fileWriter = new FileWriter("cale/catre/fișier.txt", true);
BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
bufferedWriter.write("Aceasta este o nouă linie.\n"); // Text + linie nouă
bufferedWriter.close();
```

### 6. Modificare linie in fișier
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Citirea liniilor din fisier
FileReader fileReader = new FileReader("cale/catre/fișier.txt");
BufferedReader bufferedReader = new BufferedReader(fileReader);
ArrayList<String> linii = new ArrayList<>();
int indexLinieDeModificat = 2; // Indexul liniei de modificat (0-based)
String linie;
while ((linie = bufferedReader.readLine()) != null) {
    linii.add(linie);
}
bufferedReader.close();

// Modificarea liniei specificate
linii.set(indexLinieDeModificat, "Aceasta este linia modificată.");

// Scrierea liniilor modificate înapoi în fișier
FileWriter fileWriter = new FileWriter("cale/catre/fișier.txt");
BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
for (String l : linii) {
    bufferedWriter.write(l + "\n"); // Text + linie nouă
}
bufferedWriter.close();
```

## Obiecte
Pentru clasa `Persoana`:
```java
class Persoana {
    private String nume;
    private int varsta;

    public Persoana(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
}
```

### 7. Afisare lista de obiecte (for-each)
```java
// Afișarea informațiilor despre persoane pentru ArrayList<Persoana> persoane
for (Persoana p : persoane) {
    System.out.println("Nume: " + p.getNume() + ", Vârstă: " + p.getVarsta());
}
```

### 8. Gasire primul obiect din lista de obiecte in funcție de o condiție
```java
// Gasirea primei persoane cu numele "Ion"
Persoana primaPersoana = null;
for (Persoana p : persoane) {
    if (p.getNume().equals("Ion")) {
        returna p;
    }
}
```

### 9. Sortare obiecte in funcție de un atribut
* In `Persoana` adaugă metoda `compareTo` si implementați `Comparable<Persoana>`
* Ascendent:
```java
public class Persoana implements Comparable<Persoana> {
    ...

    @Override
    public int compareTo(Persoana o) {
        if (this.varsta < o.varsta) {
            return -1; // this is less than o
        } else if (this.varsta > o.varsta) {
            return 1; // this is greater than o
        } else {
            return 0; // this is equal to o
        }
    }
}
```
* Descendent:
```java
public class Persoana implements Comparable<Persoana> {
    ...
    @Override
    public int compareTo(Persoana o) {
        if (this.varsta > o.varsta) {
            return -1; // this is greater than o
        } else if (this.varsta < o.varsta) {
            return 1; // this is less than o
        } else {
            return 0; // this is equal to o
        }
    }
}
```

* Pentru a sorta o listă de obiecte `Persoana` după vârstă:
```java
Collections.sort(persoane); // Sortare după vârstă
```

### 10. Adaugare valoare unui atribut al unui obiect
```java
// Incrementarea vârstei unei persoane
Persoana persona = new Persoana("Ion", 30);
int aniDeAdaugat = 5; // Numărul de ani de adăugat
persona.setVarsta(persona.getVarsta() + aniDeAdaugat);
```

### 11. Maximul unui atribut dintr-o lista de obiecte
```java
// Găsirea persoanei cu vârsta maximă
Persoana persoanaMaxima = null;
for (Persoana p : persoane) {
    if (persoanaMaxima == null || p.getVarsta() > persoanaMaxima.getVarsta()) {
        persoanaMaxima = p;
    }
}
System.out.println("Persoana cu vârsta maximă: " + persoanaMaxima.getNume() + ", Vârstă: " + persoanaMaxima.getVarsta());
```

### 12. Comparare două obiecte
* In `Persoana` implementați metoda `equals`:
```java
public class Persoana {
    ...
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifică dacă sunt aceleași referințe
        if (!(obj instanceof Persoana)) return false; // Verifică dacă este de tipul Persoana
        Persoana other = (Persoana) obj; // Cast la Persoana
        return this.nume.equals(other.nume) && this.varsta == other.varsta; // Compară numele și vârsta
    }
}
```
* Compararea a două obiecte `Persoana`:
```java
Persoana persoana1 = new Persoana("Ion", 30);
Persoana persoana2 = new Persoana("Ion", 30);
if (persoana1.equals(persoana2)) {
    System.out.println("Persoanele sunt egale.");
}
```

## Data si ora

### 13. Obținerea datei și orei curente
```java
import java.time.LocalDateTime;
LocalDateTime dataSiOraCurenta = LocalDateTime.now();
System.out.println("Data și ora curentă: " + dataSiOraCurenta);
```

### 14. String din data si ora
```java
import java.time.format.DateTimeFormatter;
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
String dataSiOraCaString = dataSiOraCurenta.format(formatter);
System.out.println("Data și ora ca string: " + dataSiOraCaString);
``` 

### 15. Conversie string in data si ora
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
String dataSiOraCaString = "25-12-2023 15:30:00";
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
LocalDateTime dataSiOra = LocalDateTime.parse(dataSiOraCaString, formatter);
System.out.println("Data și ora convertită: " + dataSiOra);
```

### 16. Calcularea diferenței între două date
```java
import java.time.LocalDateTime;
import java.time.Duration;
LocalDateTime data1 = LocalDateTime.of(2023, 12, 25, 15, 30);
LocalDateTime data2 = LocalDateTime.of(2023, 12, 26, 16, 45);
Duration durata = Duration.between(data1, data2);
long ore = durata.toHours();
long minute = durata.toMinutes() % 60;
System.out.println("Diferența este de " + ore + " ore și " + minute + " minute.");
```

### 17. Extragere anul, luna, ziua, ora, minutul
```java
import java.time.LocalDateTime;
LocalDateTime dataSiOra = LocalDateTime.now();
int an = dataSiOra.getYear();
int luna = dataSiOra.getMonthValue();
int ziua = dataSiOra.getDayOfMonth();
int ora = dataSiOra.getHour();
int minut = dataSiOra.getMinute();
```

## Conversii
### 18. Conversie int in string
```java
int numar = 123;
String numarCaString = String.valueOf(numar);
System.out.println("Numărul ca string: " + numarCaString);
```

### 19. Conversie string in int
```java
String numarCaString = "123";
int numar = Integer.parseInt(numarCaString);
System.out.println("Numărul ca int: " + numar);
```

## Map-uri
### 20. Creare map
```java
import java.util.HashMap;
HashMap<String, Integer> map = new HashMap<>();
map.put("cheie1", 1);
map.put("cheie2", 2);
map.put("cheie3", 3);
System.out.println("Map-ul creat: " + map);
```

### 21. Iterare prin map
```java
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String cheie = entry.getKey();
    Integer valoare = entry.getValue();
    System.out.println("Cheie: " + cheie + ", Valoare: " + valoare);
}
```

### 22. Verificare existență cheie
```java
if (map.containsKey("cheie1")) {
    System.out.println("Cheia 'cheie1' există în map.");
} else {
    System.out.println("Cheia 'cheie1' nu există în map.");
} 
```

### 23. Obținere valoare după cheie
```java
Integer valoare = map.get("cheie1");
```

#### 24. Ștergere cheie
```java
map.remove("cheie1");
```

### 25. Verificare dacă map este goală
```java
if (map.isEmpty()) {
    System.out.println("Map-ul este gol.");
}
```

## Set-uri
### 26. Creare set de obiecte
```java
import java.util.HashSet;
HashSet<Persoana> set = new HashSet<>();
set.add(new Persoana("Ion", 30));
set.add(new Persoana("Maria", 25));
set.add(new Persoana("Ion", 30)); // Nu va fi adăugat
System.out.println("Set-ul creat: " + set);
```

### 27. Iterare prin set
```java
for (Persoana p : set) {
    System.out.println("Nume: " + p.getNume() + ", Vârstă: " + p.getVarsta());
}
```

### 28. Verificare existență element
```java
// Verificare dacă set-ul conține o persoană specifică. Clasa Persoana trebuie să aibă implementată metoda equals.
if (set.contains(new Persoana("Ion", 30))) {
    System.out.println("Persoana Ion cu vârsta 30 există în set.");
} else {
    System.out.println("Persoana Ion cu vârsta 30 nu există în set.");
}
```

### 29. Ștergere element din set
```java
// Ștergere a unei persoane din set, clasa Persoana trebuie să aibă implementată metoda equals.
set.remove(new Persoana("Ion", 30)); // Va șterge persoana
```



