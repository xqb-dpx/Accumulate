<?php

class Program
{
    public $pdo = null;

    function __construct()
    {
        try {
            $pdo = new PDO("mysql:host=localhost;dbname=shop;charset=utf8", "root", "");
        } catch (PDOException $e) {
            $pdo = null;
            die("Connection failed: " . $e->getMessage());
        }
    }

    function SELECTIN($pdo, $mode)
    {
        $id = 0;
        $name = "";
        switch ($mode) {
            case "A":
                $query = $pdo->query("SELECT * FROM products");
                break;
            case "i":
                $query = $pdo->query("SELECT {$id} FROM products");
                break;
            case "N":
                $query = $pdo->query("SELECT {$name} FROM products");
                break;
        }
        $data = $query->fetchAll();
    }

    function __destruct()
    {
        $pdo = null;
    }
}

$app = new Program();
?>