<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="/">Blog</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
            @if (!Session::has('user'))
                <li class="nav-item">
                    <a class="nav-link" href="/login#login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/register#register">Register</a>
                </li>
            @endif
            @if (Session::has('user'))
                <li class="nav-item">
                    <a class="nav-link" href="/blog/create">Create</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/logout">Logout</a>
                </li>
            @endif
        </ul>
        </div>
    </div>
</nav>

<!-- Page Header -->
<header class="masthead" style="background-image: url('img/home-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
            <h1>Blog</h1>
            <span class="subheading">My Blog Page</span>
            </div>
        </div>
        </div>
    </div>
</header>