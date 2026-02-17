Nama: Ahmad Faiq Fawwaz Abdussalam

NPM: 2406397706

Kelas: Adpro - B

Link Deploy: [Deployment](consistent-sally-b-ahmadfaiqfawwaza-2406397706-e5d8eabf.koyeb.app/)

## Reflection Module 2

> 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Langkah pertama yang saya lakukan adalah membersihkan bagian import dengan menghapus org.springframework.web.bind.annotation.* yang tidak digunakan. Sebagai gantinya, saya menerapkan import yang jauh lebih spesifik, seperti @GetMapping, @PostMapping, dan @PathVariable. Selanjutnya, saya melakukan optimasi pada ProductService dengan menghilangkan modifier 'public' pada setiap metodenya. Hal ini dilakukan karena secara teknis semua metode di dalam sebuah interface sudah bersifat publik secara default. Melalui serangkaian perubahan ini, struktur kode kini menjadi jauh lebih rapi, efisien, dan memiliki tingkat keterbacaan yang lebih baik.

> 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current
     implementation has met the definition of Continuous Integration and Continuous
     Deployment? Explain the reasons (minimum 3 sentences)!

Implementasi workflow CI/CD pada GitHub Actions telah sepenuhnya memenuhi standar Continuous Integration dan Continuous Deployment. Di sisi CI, setiap push ke main branch secara otomatis memicu rangkaian pengujian, laporan code coverage, serta pemeriksaan kualitas kode melalui PMD dan integrasi Scoreboard. Proses ini menjamin bahwa setiap perubahan kode terintegrasi dengan baik dan bebas dari potensi bug statis sedini mungkin. Untuk aspek CD, aplikasi Spring Boot dikemas menggunakan Dockerfile multi-stage untuk mengoptimalkan efisiensi image sebelum dideploy secara otomatis ke Koyeb. Alur kerja yang terotomatisasi ini berhasil menciptakan pipeline yang konsisten, mengurangi risiko kesalahan manual, dan mempercepat pengiriman fitur baru ke tangan pengguna.