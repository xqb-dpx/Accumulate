<?php

namespace App\Controller;

use App\Model\UsersModel;
use Core\Controller;

class HomeController extends Controller
{
    public function index()
    {
        $users = UsersModel::select("UserID");
        $data = compact("users");
        view("Home", $data);
    }
}