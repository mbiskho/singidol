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



-- Init value for Konser
INSERT INTO konser(konser_id, nama_konser, total_pendapatan, tempat, waktu) VALUES
(1, "Debut EXO", 0, "Seoul", now()),
(2, "Pentas Seni Indonesia", 0, "Jakarta", now()),
(3, "Singapore Idol Concert", 0, "Singapore", now()),
(4, "Shopee Concert", 0, "Jakarta", now()),
(5, "NCT Dreams", 0, "Tokyo", now()),
(6, "The Singidol", 0, "Las Vegas", now());


-- Init value for Konser-Idol
INSERT INTO penampilan_konser_model(id, id_idol, id_konser, jam_mulai_tampil, idol_id, konser_id) VALUES
(1, 1, 1, "2022-10-11 07:30:00",1,1),
(2, 2, 1, "2022-10-11 08:00:00",2,1),
(3, 3, 1, "2022-10-11 08:30:00",3,1),
(4, 3, 2, "2022-10-11 08:30:00",3,2),
(5, 4, 2, "2022-10-11 08:35:00",4,2),
(6, 5, 2, "2022-10-11 08:40:00",5,2),
(7, 1, 2, "2022-10-11 08:45:00",1,2),
(8, 1, 3, "2022-10-11 08:00:00",1,3),
(9, 3, 3, "2022-10-11 08:30:00",3,3),
(10, 3, 4, "2022-10-11 08:15:00",3,4),
(11, 4, 4, "2022-10-11 08:30:00",4,4),
(12, 5, 4, "2022-10-11 08:45:00",5,4),
(13, 1, 5, "2022-10-11 09:00:00",1,5),
(14, 2, 5, "2022-10-11 09:30:00",2,5),
(15, 3, 5, "2022-10-11 08:45:00",3,5),
(16, 4, 5, "2022-10-11 08:15:00",4,5),
(17, 5, 6, "2022-10-11 10:00:00",5,6),
(18, 4, 6, "2022-10-11 11:00:00",4,6);