<?php
    require_once '../connect.php';
    $json = json_decode(file_get_contents('php://input'),true);

    $nama_menu = $json['nama_menu'];
    $harga = $json['harga'];
    $jenis = $json['jenis'];
    $create_by = $json['create_by'];
    $image = $json['image'];

    $response = array();

    $decodedImage = base64_decode($image);
	$nama_gambar = $nama_menu.".JPG";
    $return = file_put_contents("../image/".$nama_gambar, $decodedImage);

    $sql = "INSERT INTO tb_menu(nama_menu,harga,gambar,jenis,create_by)
                VALUES ('$nama_menu','$harga','$nama_gambar','$jenis','$create_by')";
        $query = mysqli_query($conn,$sql);
        if ($query){
            $response['success'] = 1;
            $response['message'] = "Create Data Successfully";
        }else{
            $response['success'] = 0;
            $response['message'] = "Create Data Failed";
        }
        header('Content-Type: application/json');
        echo json_encode($response);

?>