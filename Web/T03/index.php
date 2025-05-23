<!doctype html>
<html dir="rtl" lang="fa">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="assets/bootstrap-v5.3.5/css/bootstrap.rtl.min.css" rel="stylesheet"/>
    <link href="assets/custom.css" rel="stylesheet"/>
    <title>شرکت داده پردازی سیمرغ</title>
</head>
<body>
<a name="index"></a>
<?php
include "include/header.php";
?>
<div id="mywebsite-slider" class="carousel slide mb-3" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#mywebsite-slider" data-bs-slide-to="0" class="active" aria-current="true"
                aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#mywebsite-slider" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#mywebsite-slider" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="images/slider.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="images/slider.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="images/slider.jpg" class="d-block w-100" alt="...">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#mywebsite-slider" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#mywebsite-slider" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
<div class="container">
    <div class="row">
        <div class="col my-3">
            <h2 class="fw-bold text-center"><a name="product">محصولات</a></h2>
        </div>
    </div>
    <?php
    try {
        $db = new PDO("mysql:host=localhost;dbname=shop;charset=utf8;", "root", "");
    } catch (PDOException $e) {
        die($e->getMessage());
    }

    $query = $db->prepare("SELECT id, title, summary FROM products ORDER BY time DESC LIMIT 6");
    $data = $query->fetchAll();
    foreach ($data as $item) {
        ?>
        <div class="row row-cols-lg-3 row-cols-md-2 row-cols-sm-1 row-cols-1">
            <div class="col mb-3">
                <div class="card shadow-sm">
                    <img src="images/product.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title"><?= $item["title"] ?></h5>
                        <p class="card-text"><?= $item["summary"] ?></p>
                        <a href="#" class="btn btn-primary">Details...</a>
                    </div>
                </div>
            </div>
        </div>
        <?php
    }
    ?>
</div>
<section class="py-4 bg-light shadow-sm">
    <div class="container">
        <div class="row">
            <div class="col my-3">
                <h2 class="fw-bold text-center"><a name="about">درباره ما</a></h2>
            </div>
        </div>
        <div class="row align-items-center">
            <div class="col-lg-3 col-md-5 d-md-block d-sm-none d-none"><img class="img-fluid" src="images/about.png"
                                                                            alt=""/></div>
            <div class="col-lg-9 col-md-7 col-sm-12 col-12">
                <p class="m-0 text-justify lh-lg">لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با
                    استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و
                    برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای
                    زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها
                    شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد
                    کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به
                    پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود
                    طراحی اساسا مورد استفاده قرار گیرد.</p>
            </div>
        </div>
    </div>
</section>
<section class="py-4">
    <div class="container">
        <div class="row">
            <div class="col my-3">
                <h2 class="fw-bold text-center"><a name="feedback">ارتباط با ما</a></h2>
            </div>
        </div>
        <div class="row align-items-center">
            <div class="col-md-6 col-sm-12 col-12">
                <p>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-pin-map" viewBox="0 0 16 16">
                        <path fill-rule="evenodd"
                              d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8l3-4z"/>
                        <path fill-rule="evenodd"
                              d="M8 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"/>
                    </svg>
                    آدرس: اراک، کوی معلم، بلوار شهدای دولت، دانشگاه فنی و حرفه ای استان مرکزی
                </p>
                <p>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-envelope" viewBox="0 0 16 16">
                        <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2zm13 2.383-4.758 2.855L15 11.114v-5.73zm-.034 6.878L9.271 8.82 8 9.583 6.728 8.82l-5.694 3.44A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.739zM1 11.114l4.758-2.876L1 5.383v5.73z"/>
                    </svg>
                    صندوق پستی: 145547-555123
                </p>
                <p>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-mailbox" viewBox="0 0 16 16">
                        <path d="M4 4a3 3 0 0 0-3 3v6h6V7a3 3 0 0 0-3-3zm0-1h8a4 4 0 0 1 4 4v6a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V7a4 4 0 0 1 4-4zm2.646 1A3.99 3.99 0 0 1 8 7v6h7V7a3 3 0 0 0-3-3H6.646z"/>
                        <path d="M11.793 8.5H9v-1h5a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.354-.146l-.853-.854zM5 7c0 .552-.448 0-1 0s-1 .552-1 0a1 1 0 0 1 2 0z"/>
                    </svg>
                    پست الکترونیکی: info[at]simurghdp[dot]ir
                </p>
                <p>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-telephone" viewBox="0 0 16 16">
                        <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
                    </svg>
                    تلفن تماس: 33411000 - 086
                </p>
                <p>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-printer" viewBox="0 0 16 16">
                        <path d="M2.5 8a.5.5 0 1 0 0-1 .5.5 0 0 0 0 1z"/>
                        <path d="M5 1a2 2 0 0 0-2 2v2H2a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h1v1a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2v-1h1a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2h-1V3a2 2 0 0 0-2-2H5zM4 3a1 1 0 0 1 1-1h6a1 1 0 0 1 1 1v2H4V3zm1 5a2 2 0 0 0-2 2v1H2a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-1v-1a2 2 0 0 0-2-2H5zm7 2v3a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1v-3a1 1 0 0 1 1-1h6a1 1 0 0 1 1 1z"/>
                    </svg>
                    نمابر: 33411000 - 086
                </p>
                <p class="mb-md-0 mb-sm-3 mb-3">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-phone" viewBox="0 0 16 16">
                        <path d="M11 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h6zM5 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H5z"/>
                        <path d="M8 14a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                    </svg>
                    پیامک: 3000862121
                </p>
            </div>
            <div class="col-md-6 col-sm-12 col-12">
                <?php
                if (isset($_POST['submit'])) {
                    if (isset($_POST['name']) and !empty($_POST['name']) && isset($_POST['email']) and !empty($_POST['email']) && isset($_POST['message']) and !empty($_POST['message'])) {
                        $query = $db->prepare("INSERT INTO feedback (name, email, message, ip, time) VALUES (?, ?, ?, ?, ?)");
                        $data = [$_POST['name'], $_POST['email'], $_POST['message'], $_SERVER['REMOTE_ADDR'], time()];
                        if ($query->execute($data)) {
                        ?>
                        <script type="javascript">
                            alert("Feedback submit successfully!");
                        </script>
                        <?php
                        }
                    } else {
                        ?>
                        <script type="javascript">
                            alert("An input is empty! please try again...");
                        </script>
                        <?php
                    }
                }
                ?>
                <form action="index.php" method="POST">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="name" name="name" placeholder="*" required/>
                        <label for="name">نام و نام خانوادگی</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="email" name="email" placeholder="*" required/>
                        <label for="email">پست الکترونیکی</label>
                    </div>
                    <div class="form-floating mb-3">
                        <textarea class="form-control" placeholder="*" id="message" name="message" required></textarea>
                        <label for="message">پیام</label>
                    </div>
                    <input type="submit" class="btn btn-success" id="submit" name="submit" value="ارسال پیام"/>
                    <input type="reset" class="btn btn-secondary" id="reset" name="reset" value="بازنشانی فرم"/>
                </form>
            </div>
        </div>
    </div>
</section>
<?php
include 'include/footer.php';
?>
<script src="assets/bootstrap-v5.3.5/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<?php
$db = null;
$query = null;
?>