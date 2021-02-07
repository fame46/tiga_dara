<?php
require_once('../connect.php');

$username = $_POST['username'];
$password = md5($_POST['password']);
$level = $_POST['level'];
$created_by = $_POST['created_by'];

$query1 = "SELECT * FROM tb_user WHERE username = '$username'";
    $result = mysqli_query($conn,$query1);
    $rowCount = mysqli_num_rows($result);

    if($rowCount > 0 ){
        $response['success'] = -1;
        $response['message'] = "akun is already exist";
    }else{
        $sql = "INSERT INTO tb_user(username,password,level,created_by)
        VALUES ('$username','$password','$level','$created_by')";
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