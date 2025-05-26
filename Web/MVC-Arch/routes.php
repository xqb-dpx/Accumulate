<?php

use App\Controller\HomeController;
use Core\Router;

Router::getRoutes(route: "/", controller: HomeController::class, action: "index");