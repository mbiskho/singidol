-- Initial value for Idol
INSERT into idol(idol_id ,asal_negara, jumlah_anggota, nama_idol, tanggal_debut)
values 
(1,"Indonesia", 5, "SMASH", NOW()),
(2,"Korea", 30, "BTS", NOW()),
(3,"Indonesia", 48, "JKT48", NOW()),
(4,"Korea", 30, "EXO", NOW()),
(5,"China", 30, "XNINE", NOW());



-- Init value for Tipe
INSERT INTO tipe (tipe_id,nama ,deskripsi_tipe , harga) VALUES
 (1,'vip','Tiket VIP',1200000)
,(2,'platinum','Tiket Platinum',1000000)
,(3,'gold','Tiket Gold',800000)
,(4,'silver','Tiket Silver',600000);