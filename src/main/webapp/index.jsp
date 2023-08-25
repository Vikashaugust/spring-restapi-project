<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Spring Boot File Upload</title>
        <link rel="stylesheet" href="/css/main.css" />

        <link
            	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            	rel="stylesheet"
            	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            	crossorigin="anonymous">
    </head>

    <body class="bg-light" >



<h1 class="text-center" >File Management Using Rest API</h1>

     <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <p class="text-center" fs-3> <h3>File Upload</h3></p>
                            <form method="post" action="uploadfile" enctype="multipart/form-data">
                                <div class="mb-3">
                                    <label>File</label>
                                    <input type="file" name="files" class="form-cont">
                                </div>
                                <div class="text-center"><button class="btn btn-primary" type="button" aria-expanded="false">Upload</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<br>

         <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <p class="text-center" fs-3><h3> File Delete</h3></p>
                                <form>
                                    <div class="mb-3">
                                        <label>File</label>
                                        <input type="file" name="files" class="form-cont">
                                    </div>
                                    <div class="text-center"><button class="btn btn-primary" type="button" aria-expanded="false">Delete</button>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

 <br>

             <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body" >
                                            <p class="text-center" fs-3><h3> Rename File</h3></p>
                                            <form>
                                                <div class="mb-3">
                                                    <label>File</label>
                                                    <input type="file" name="files" class="form-cont">

                                                </div>
                                                <div class="mb-3">
                                                               <label> Rename File</label>
                                                               <input type="text" name="texts" class="form-cont">

                                                </div>
                                                <div class="text-center"><button class="btn btn-primary" type="button" aria-expanded="false">Rename</button>
                                                </div>

                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

      <br>


                        <div class="container">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <p class="text-center" fs-3><h3> File Move</h3></p>
                                                        <form>
                                                            <div class="mb-3">
                                                                <label>File</label>
                                                                <input type="file" name="files" class="form-cont">
                                                            </div>
                                                            <div class="text-center"><button class="btn btn-primary" type="button" aria-expanded="false">Move</button>
                                                            </div>

                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

           <br>


                                           <style>
                                            .card-body {
                                              border: 5px outset blue;
                                              background-color: lightblue;
                                              text-align: center;
                                            }
                                            </style>



    </body>
</html>