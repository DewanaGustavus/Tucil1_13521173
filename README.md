# Tucil1_13521173

## Tugas Kecil 1 IF2211 Strategi Algoritma

### SEMESTER 2 TAHUN 2022/2023

### Penyelesaian Permainan Kartu 24 dengan Algoritma Brute Force

### Deskripsi Program


### Requirement Program

- Agar dapat menjalankan program dibutuhkan java dengan versi 18.0.2.1 keatas.
- Untuk mengecek versi java dapat menggunakan command

```sh
java -version
```

- Untuk menginstall java dapat mendownload pada <a href="https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html">link</a>.
- Clone repository ini ke dalam komputer anda dengan menggunakan command berikut.

```sh
git clone https://github.com/DewanaGustavus/Tucil1_13521173.git
```

### Cara Menjalankan Program

- Setelah melakukan clone program berada pada folder bin, untuk berpindah ke folder bin dapat dilakukan dengan menggunakan command berikut.

```sh
cd Tucil1_13521173
cd bin
```

- Ada 3 pilihan untuk menjalakan program yaitu menjalankan java class, menjalankan java jar, menjalankan executable
- Untuk menjalankan java class dapat dilakukan dengan menggunakan command

```sh
java src.GUI
```

- Untuk menjalankan java jar dapat dilakukan dengan command

```sh
java -jar game.jar
```

- Untuk menjalankan executable dapaat dilakukan dengan command

```sh
game.exe
```

### Cara Mengkompilasi Program

- Untuk mengkompilasi program pertama-tama pindah ke folder Tucil1_13521173

```
cd Tucil1_13521173
```

- Untuk mengkompilasi code java dapat menggunakan command

```
javac -d <Folder> src/*.java
```

- Setelah mengkompilasi code, program membutuhkan data gambar kartu yang terletak pada src\img, anda dapat menambah gambar dengan meng-copy paste manual atau dengan menggunakan command berikut (command menggunakan linux / WSL)

```
cp -r ./src/img ./<Folder>/src/img
```

- Contoh kompilasi ke folder bin (command dieksekusi pada WSL)
javac -d bin src/*.java
cp -r ./src/img ./bin/src/img

```

### Cara Menggunakan Program


### Identitas Pembuat
Nama : Dewana Gustavus Haraka Otang
NIM : 13521173



jar cvf game.jar -C src/ .
unzip -o game.jar META-INT/MANIFEST.MF
zip game.jar META-INF/MANIFEST.MF
