<?php
require_once('../connect.php');

$meja = $_POST['meja'];
$created_by = $_POST['created_by'];

$query1 = "SELECT * FROM tb_meja WHERE meja = '$meja'";
    $result = mysqli_query($conn,$query1);
    $rowCount = mysqli_num_rows($result);

    if($rowCount > 0 ){
        $response['success'] = -1;
        $response['message'] = "akun is already exist";
    }else{
        $sql = "INSERT INTO tb_meja(meja,created_by)
        VALUES ('$meja','$created_by')";
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