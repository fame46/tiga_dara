<?php
require_once('../connect.php');

$nama_menu = $_POST['nama_menu'];
$harga_menu = $_POST['harga_menu'];
$jumlah = $_POST['jumlah'];
$keterangan = $_POST['keterangan'];
$pelayan = $_POST['pelayan'];
$subTotal = $_POST['subTotal'];

$query1 = "SELECT * FROM tb_pesanan WHERE nama_menu = '$nama_menu' AND pelayan ='$pelayan' AND keterangan ='$keterangan' AND status = 0";
    $result = mysqli_query($conn,$query1);
    $rowCount = mysqli_num_rows($result);

    if($rowCount > 0 ){
        $d = mysqli_fetch_array($result);
        $jumlah2 = $d['jumlah'];
        $jumlahfix = $jumlah+$jumlah2;
        $sql2 = "UPDATE tb_pesanan SET jumlah = $jumlahfix , subTotal = $jumlahfix * $harga_menu, modified_on = now(), modified_by = '$pelayan' WHERE nama_menu = '$nama_menu' AND pelayan ='$pelayan' AND keterangan ='$keterangan' AND status = 0";
        $query2 = mysqli_query($conn,$sql2);
        if ($query2){
            $response['success'] = 1;
            $response['message'] = "Create Data Successfully";
        }else{
            $response['success'] = 0;
            $response['message'] = "Create Data Failed";
        }
        // $response['jumlahfix'] = $jumlah+$jumlah2;
    }else{
        $sql = "INSERT INTO tb_pesanan(nama_menu,harga_menu,subTotal,jumlah,keterangan,status,pelayan,create_by)
        VALUES ('$nama_menu','$harga_menu','$subTotal','$jumlah','$keterangan',0,'$pelayan','$pelayan')";
        $query = mysqli_query($conn,$sql);
        if ($query){
            $response['success'] = 1;
            $response['message'] = "Create Data Successfully";
        }else{
            $response['success'] = 0;
            $response['message'] = "Create Data Failed";
        }
    }
    echo json_encode($response);
?>