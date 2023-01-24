# Tucil1_13521173

## Tugas Kecil 1 IF2211 Strategi Algoritma

### SEMESTER 2 TAHUN 2022/2023

### Penyelesaian Permainan Kartu 24 dengan Algoritma Brute Force

### Deskripsi Program

Prosedur algoritma bruteforce yang digunakan untuk menyelesaikan masalah adalah sebagai berikut:

1. Urutkan kartu sebelum mencari seluruh permutasi kartu
2. Simpan seluruh permutasi kartu yang mungkin
3. Cari dan simpan seluruh kombinasi operasi yang mungkin
4. Iterasi seluruh permutasi kartu
5. Untuk setiap permutasi kartu, Iterasi kombinasi operasi
6. Untuk setiap kombinasi operasi, Iterasi Seluruh kemungkinan urutan melakukan operasi
7. Simpan solusi pada sebuah list yang berisi string yang menyatakan bagaimana susunan kartu serta operasi dan urutan operasi yang digunakan sehingga dapat menghasilkan angka 24.

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

- Apabila ingin mengkompilasi program menjadi executable perlu mendownload tools <a href = "https://launch4j.sourceforge.net">Launch4j</a>.

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

- Untuk menjalankan executable dapat dilakukan dengan command (untuk menjalankan executable pastikan terdapat file jar game.jar pada folder yang sama)

```sh
./game.exe
```

### Cara Mengkompilasi Program

- Proses mengkompilasi program terbagi atas 3 tahap yaitu membuat java class, membuat java jar, membaut executable. Pengguna dapat berhenti sesuai dengan cara penggunaan yang diinginkan

- Seluruh command di eksekusi pada command line linux, dan hasil kompilasi akan disimpan pada folder bin.

- Untuk mengkompilasi program pertama-tama pindah ke folder Tucil1_13521173

```sh
cd Tucil1_13521173
```

- Untuk mengkompilasi code java menjadi dapat menggunakan command

```sh
javac -d bin src/*.java
```

- Setelah mengkompilasi code, program membutuhkan data gambar kartu yang terletak pada src/img, anda dapat menambah gambar dengan meng-copy paste manual atau dengan menggunakan command berikut

```sh
cp -r ./src/img ./bin/src/img
```

- Untuk membuat java JAR pertama-tama zip java class menjadi JAR dengan command

```sh
jar cvf bin/game.jar -C bin/ .
```

- Sebelum mengeksekusi JAR kita harus mengubah data MANIFEST.MF di dalam JAR

- Untuk mengubah file MANIFEST kita perlu meng-unzip file MANIFEST dengan command

```sh
unzip -o bin/game.jar META-INF/MANIFEST.MF
```

- Untuk mengubah isi file MANIFEST kita dapat menggunakan command

```sh
nano META-INF/MANIFEST.MF
```

- kita perlu menambahkan kalimat berikut pada file MANIFEST

```sh
Main-Class: src.GUI
```

- Untuk keluar dari editor dan men-save file MANIFEST dapat dilakukan dengan shortcut <kbd>Ctrl</kbd> + <kbd>S</kbd> ⇨ <kbd>Ctrl</kbd> + <kbd>X</kbd>

- Untuk memasukkan kembali file MANIFEST kedalam JAR dapat menggunakan command

```sh
zip bin/game.jar META-INF/MANIFEST.MF
```

- Untuk membuat executable pertama-tama buka tools Launch4j

- Pada bagian Basic, isi kolom Output file dengan path Tucil1_13521173/bin, isi kolom Jar runtime path dengan game.jar, dan "centang don't wrap the jar, launch only" untuk menghindari antivirus false positive, contoh pengisian seperti berikut.

![](https://cdn.discordapp.com/attachments/702842263704961064/1066760723142803556/image.png)

- Pada bagian JRE isi bagian Min JRE version dengan 1.18.0, contoh pengisian seperti berikut.

![](https://cdn.discordapp.com/attachments/702842263704961064/1066761409679081543/image.png)

- Untuk membuat executable klik logo gerigi pada bagian kiri atas layar, tools akan meminta untuk menyimpan file konfigurasi xml, isi bagian ini dengan game lalu tekan <kbd>Enter</kbd>.

![](https://cdn.discordapp.com/attachments/702842263704961064/1067022640214188052/image.png)

- Setelah seluruh langkah dilakukan executable sudah berhasil dibuat.

### Cara Menggunakan Program

- Untuk memilih kartu yang digunakan dapat menggunakan tombol random apabila ingin memilih kartu secara acak, atau dapat juga memilih kartu secara spesifik dengan mengubah nilai kartu dengan list yang berada dibawah gambar kartu.

![](https://cdn.discordapp.com/attachments/702842263704961064/1067024748447547432/image.png)

- Untuk mengeluarkan solusi persoalan dapat dilakukan dengan menekan tombol solve dan pada bagian tengah layar akan ditampilkan seluruh solusi.

![](https://cdn.discordapp.com/attachments/702842263704961064/1067025483528683561/image.png)

- Setelah menekan tombol solve program juga akan menampilkan informasi mengenai jumlah solusi yang ada, berapa lama waktu eksekusi algoritma, dan tombol save yang dapat digunakan untuk menyimpan solusi.

![](https://cdn.discordapp.com/attachments/702842263704961064/1067025333670379571/image.png)

### Identitas Pembuat

Nama : Dewana Gustavus Haraka Otang

NIM : 13521173
