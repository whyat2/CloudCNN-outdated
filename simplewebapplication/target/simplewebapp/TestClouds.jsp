<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="myStyles.css">
    </head>
    <body>
        <div>
            <div class="Navigation">
                <a href="/simplewebapp">
                    <button class="SpanningButton">Home</button>
                </a>
                <a href="/simplewebapp/search">
                    <button class="SpanningButton">SearchWeather</button>
                </a>
                <a href="/simplewebapp/AI">
                    <button class="SpanningButton">Generate Clouds</button>
                </a>
            </div>
            <div>
                <h1>
                    Collect Clouds
                </h1>
                <div>
                    <h2 class="CloudTypeIntro">
                        &emsp;Enter your cloud png and our neural network will
                        classify it as one of 6 types:
                    </h2>
                    <ol class="CloudTypes">
                        <li class="listItem">Clear <br>Sky</li>
                        <li class="listItem">Patterned <br>Clouds</li>
                        <li class="listItem">Thin White <br>Clouds</li>
                        <li class="listItem">Thick White <br>Clouds</li>
                        <li class="listItem">Thick Dark <br>Clouds</li>
                        <li class="listItem">Veil <br>Clouds</li>
                    </ol>
                </div>
            </div>
            <div class="Classifier">
                <div class="SpanningButton">
                    <form action="/simplewebapp/AI" method="post" enctype="multipart/form-data">
                        <input type="file" name="file" accept=".png">
                        <input type="submit">
                    </form>
                </div>
                <div>
                    <h1>Your Cloud is: </h1>
                    <h2>${cloudImage}</h2>
                </div>
            </div>
            <div>
                <p>
                    Dataset Used to help classify our AI:
                </p>
                <p>
                    Truong Hoang, Vinh (2020), “Cloud-ImVN 1.0”, 
                    Mendeley Data, V2, doi: 10.17632/vwdd9grvdp.2
                </p>
            </div>
        </div>
    </body>
</html>