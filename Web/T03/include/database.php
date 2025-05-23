<?php

class Program
{
    public $db = null;

    function __construct()
    {
        try {
            $db = new PDO("mysql:host=localhost;dbname=shop;charset=utf8", "root", "");
        } catch (PDOException $e) {
            $db = null;
            die("Connection failed: " . $e->getMessage());
        }
    }

    function __destruct()
    {
        $db = null;
    }
}

$app = new Program();
?>