<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$kasir = $_POST['kasir'];
	$atasnama = $_POST['atasnama'];
	$nomeja = $_POST['nomeja'];
	$id = $_POST['id'];
	$jumar = $_POST['count'];

	require_once '../connect.php';

	//Membuang Koma terakhir
	$id = substr($id,0,strlen($id)-1);

	//Inisialisasi Arrays
	$arrayid =array();

	//Memisah Karakter berdasarkan Coma
	$partid = explode(",",$id);

	for($i = 0; $i < $jumar; $i++){
		//Push Ke Arrays
		array_push( $arrayid, $partid[$i] = trim($partid[$i]));
	}

	$query  = null;
	for($c = 0 ; $c<$jumar; $c++){
		//Insert Data Menggunakan Looping Berdasrkan Posisi Arrays [$c]

		$sql = "UPDATE tb_pesanan SET status = 2, kasir = '$kasir', modified_on = now(), modified_by = '$kasir'
                    WHERE atasnama = '$atasnama' AND nomeja = '$nomeja' AND status = 1 AND id = '$arrayid[$c]'";
		$query = mysqli_query($conn,$sql);
	}
	if($query){
		echo "BERHASIL";
	}

}

?>