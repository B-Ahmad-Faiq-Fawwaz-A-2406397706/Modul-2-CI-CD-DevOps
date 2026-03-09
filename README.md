Nama: Ahmad Faiq Fawwaz Abdussalam

NPM: 2406397706

Kelas: Adpro - B

Link Deploy: [Deployment](https://consistent-sally-b-ahmadfaiqfawwaza-2406397706-e5d8eabf.koyeb.app/)

<details>
<summary>Reflection Module 2</summary>

## Reflection Module 2

> 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Langkah pertama yang saya lakukan adalah membersihkan bagian import dengan menghapus org.springframework.web.bind.annotation.* yang tidak digunakan. Sebagai gantinya, saya menerapkan import yang jauh lebih spesifik, seperti @GetMapping, @PostMapping, dan @PathVariable. Selanjutnya, saya melakukan optimasi pada ProductService dengan menghilangkan modifier 'public' pada setiap metodenya. Hal ini dilakukan karena secara teknis semua metode di dalam sebuah interface sudah bersifat publik secara default. Melalui serangkaian perubahan ini, struktur kode kini menjadi jauh lebih rapi, efisien, dan memiliki tingkat keterbacaan yang lebih baik.

> 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current
     implementation has met the definition of Continuous Integration and Continuous
     Deployment? Explain the reasons (minimum 3 sentences)!

Implementasi workflow CI/CD pada GitHub Actions telah sepenuhnya memenuhi standar Continuous Integration dan Continuous Deployment. Di sisi CI, setiap push ke main branch secara otomatis memicu rangkaian pengujian, laporan code coverage, serta pemeriksaan kualitas kode melalui PMD dan integrasi Scoreboard. Proses ini menjamin bahwa setiap perubahan kode terintegrasi dengan baik dan bebas dari potensi bug statis sedini mungkin. Untuk aspek CD, aplikasi Spring Boot dikemas menggunakan Dockerfile multi-stage untuk mengoptimalkan efisiensi image sebelum dideploy secara otomatis ke Koyeb. Alur kerja yang terotomatisasi ini berhasil menciptakan pipeline yang konsisten, mengurangi risiko kesalahan manual, dan mempercepat pengiriman fitur baru ke tangan pengguna.
</details>

<details>
<summary>Reflection Module 3</summary>

## Reflection Module 3

> 1. Explain what principles you apply to your project!

1. Single Responsibility Principle (SRP)

Saya menerapkan prinsip SRP dengan memastikan setiap kelas memiliki satu tanggung jawab yang jelas. Misalnya, ProductController hanya bertanggung jawab untuk menangani permintaan HTTP dan delegasi logika bisnis ke ProductService, sementara ProductService fokus pada logika bisnis terkait produk. Dengan memisahkan tanggung jawab ini, kode menjadi lebih modular, mudah dipahami, dan lebih mudah untuk diuji.

2. Open/Closed Principle (OCP)

Saya mengikuti prinsip OCP dengan merancang kelas dan metode yang dapat diperluas tanpa perlu mengubah kode yang sudah ada. Misalnya, jika saya ingin menambahkan fitur baru di ProductRepository, saya dapat melakukannya dengan menambahkan metode baru tanpa harus memodifikasi metode yang sudah ada. Hal ini membantu menjaga stabilitas kode dan meminimalkan risiko bug saat menambahkan fitur baru.

3. Liskov Substitution Principle (LSP)

Saya memastikan bahwa setiap kelas turunan dapat menggantikan kelas induknya tanpa mengubah perilaku yang diharapkan. Misalnya, CarServiceImpl mengimplementasikan semua metode yang didefinisikan dalam CarService, sehingga saya dapat menggunakan CarServiceImpl di mana pun CarService digunakan tanpa khawatir tentang ketidakcocokan tipe atau perilaku yang tidak diinginkan. Dengan mematuhi prinsip ini, kode menjadi lebih fleksibel dan mudah untuk diperluas di masa depan.

4. Interface Segregation Principle (ISP)

Saya memastikan bahwa setiap interface memiliki tanggung jawab yang spesifik dan tidak memaksa kelas untuk mengimplementasikan metode yang tidak mereka butuhkan. Misalnya, saya memisahkan CarService dan ProductService menjadi dua interface yang berbeda, sehingga kelas yang hanya membutuhkan layanan terkait mobil tidak perlu mengimplementasikan metode yang tidak relevan. Dengan mematuhi prinsip ini, kode menjadi lebih modular dan mudah untuk dipelihara.

5. Dependency Inversion Principle (DIP)

Saya mengikuti prinsip DIP dengan memastikan bahwa kelas-kelas tingkat tinggi tidak bergantung pada kelas-kelas tingkat rendah, melainkan keduanya bergantung pada abstraksi. Misalnya, ProductController bergantung pada ProductService sebagai abstraksi, bukan pada implementasi konkret seperti ProductServiceImpl. Dengan menggunakan dependency injection, saya dapat dengan mudah mengganti implementasi ProductService tanpa harus mengubah kode di ProductController, sehingga meningkatkan fleksibilitas dan memudahkan pengujian unit.

> 2. Explain the advantages of applying SOLID principles to your project with examples.

1. Meningkatkan Maintainability

Dengan menerapkan prinsip SOLID, kode menjadi lebih modular dan terorganisir dengan baik, sehingga memudahkan pemeliharaan. Misalnya, dengan mengikuti Single Responsibility Principle, setiap kelas memiliki satu tanggung jawab yang jelas, sehingga ketika ada perubahan yang diperlukan, kita hanya perlu memodifikasi kelas yang relevan tanpa harus khawatir tentang efek samping pada bagian lain dari kode.

2. Meningkatkan Fleksibilitas

Prinsip SOLID memungkinkan kita untuk dengan mudah menambahkan fitur baru tanpa harus mengubah kode yang sudah ada. Misalnya, dengan mengikuti Open/Closed Principle, kita dapat menambahkan metode baru di ProductRepository tanpa harus memodifikasi metode yang sudah ada, sehingga menjaga stabilitas kode dan meminimalkan risiko bug.

3. Meningkatkan Testability

Dengan mematuhi prinsip SOLID, kode menjadi lebih mudah untuk diuji. Misalnya, dengan mengikuti Dependency Inversion Principle, kita dapat menggunakan dependency injection untuk mengganti implementasi konkret dengan mock atau stub saat melakukan pengujian unit, sehingga memungkinkan kita untuk menguji komponen secara terisolasi dan memastikan bahwa mereka berfungsi dengan benar tanpa bergantung pada implementasi lain yang mungkin belum stabil.

> 3. Explain the disadvantages of not applying SOLID principles to your project with examples.

1. Skalabilitas yang Buruk

Tanpa menerapkan prinsip SOLID, kode dapat menjadi sulit untuk diperluas dan diubah seiring waktu. Misalnya, jika kita tidak mengikuti Single Responsibility Principle, kita mungkin memiliki kelas yang menangani banyak tanggung jawab sekaligus, sehingga ketika kita perlu menambahkan fitur baru atau memperbaiki bug, kita harus memodifikasi kelas tersebut, yang dapat menyebabkan efek samping yang tidak diinginkan pada bagian lain dari kode.

2. Tight Coupling (Ketergantungan yang Kuat)

Tanpa mengikuti prinsip SOLID, kita mungkin memiliki kode yang sangat tergantung satu sama lain, sehingga sulit untuk memodifikasi atau mengganti bagian tertentu tanpa mempengaruhi bagian lain. Misalnya, jika kita tidak mengikuti Dependency Inversion Principle, kita mungkin memiliki kelas-kelas tingkat tinggi yang bergantung langsung pada kelas-kelas tingkat rendah, sehingga ketika kita perlu mengganti implementasi konkret, kita harus memodifikasi kode di banyak tempat, yang dapat menyebabkan bug dan kesalahan.

3. Code Bloat (Kelebihan Kode)

Tanpa menerapkan prinsip SOLID, kode dapat menjadi berantakan dan sulit untuk dipahami. Misalnya, jika kita tidak mengikuti Interface Segregation Principle, kita mungkin memiliki interface yang terlalu besar dengan banyak metode yang tidak relevan untuk semua implementasi, sehingga membuat kode menjadi sulit untuk dipahami dan dipelihara, karena pengembang harus memahami semua metode dalam interface tersebut bahkan jika mereka hanya membutuhkan sebagian kecil dari fungsionalitas yang disediakan.
</details>

## Reflection Module 4

> 1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best 
Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this 
TDD flow is useful enough for you or not. If not, explain things that you need to do next time 
you make more tests. 

Alur TDD yang diikuti dalam tutorial ini cukup membantu karena memaksa saya untuk memikirkan ekspektasi dan perilaku kode sebelum menulisnya, sehingga desain kelas menjadi lebih terarah dan bug dapat terdeteksi lebih awal. Mengacu pada pertanyaan reflektif Percival (2017), pengujian yang dibuat sudah mencakup happy path dan unhappy path, namun masih bisa diperluas untuk kasus-kasus edge yang lebih spesifik seperti input null atau karakter khusus pada author. 

> 2. You have created unit tests in Tutorial. Now reflect whether your tests have successfully
     followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you
     create more tests. 

Untuk prinsip F.I.R.S.T., unit test yang dibuat sudah cukup Fast karena tidak bergantung pada database nyata, sudah Independent karena setiap test memiliki setUp sendiri, Repeatable karena hasilnya konsisten di environment manapun, Self-validating karena menggunakan assertion yang jelas, serta Timely karena ditulis sebelum implementasi, sehingga secara keseluruhan prinsip F.I.R.S.T. sudah terpenuhi dengan baik meskipun ke depannya cakupan test perlu ditambah untuk meningkatkan branch coverage.