<?php
try{
    $dbh = new PDO("mysql:host=localhost;dbname=course", "root", "");
    $query = $dbh->prepare("INSERT INTO user_data (Username, User_Password, Nickname, Phone_Number) VALUES (?, ?, ?, ?)");
    $data = array('');
    if (!$dbh){
        die("Connection failed: " . mysqli_connect_error());
    }
    else {
        if ($query->execute()) {
            echo "Query Running...";
            echo "<br>";
            $statement = $dbh->query("SELECT * FROM user_data");
            while ($row = $statement->fetch()) {
                echo 'ID: ' . $row['User_ID'] . '<br>';
                echo 'Username: ' . $row['Username'] . '<br>';
            }
            $dbh = null;
            $query = null;
        }
    }
}
catch (PDOException $e){
    echo $e->getMessage();
}