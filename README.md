<div id="top"></div>


<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

<!--
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]
-->
[![MIT License][license-shield]][license-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/logo.png" alt="Logo" width="250" height="150">
  </a>

  <h3 align="center">Shop Fast Application</h3>

  <p align="center">
    A microservice based architecture using spring
    <!--
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Report Bug</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Request Feature</a>
-->
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://https://ineffablekenobi.github.io/Shopfast2.0/)

This is the API diagram. This diagram shows how the API is structurally built and how they commmunicate with each other.
This a microservice based API designed for those who wants a backend for their ecommerce. 


  </br>
  </br>
  </br>

<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

  </br>
  </br>
  </br>
  
  
  <div align = "center">
    <img src = "images/springlogo.png" width="600" height="300" >
  </div>
  </div>
  </br>
  </br>
  </br>
  
  <div>
  <div align = "center">
    <img src = "images/NetflixOss.jpeg" width="600" height="300" >
  </div>
  
  </br>
  </br>
  </br>

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Cloud](https://spring.io/projects/spring-cloud)
* [Spring Security](https://spring.io/projects/spring-security)
* [Spring Data](https://spring.io/projects/spring-data)
* [Netflix Eureka](https://github.com/Netflix/eureka)
* [Netflix Ribbon](https://github.com/Netflix/ribbon)
* [Netflix Hystrix](https://github.com/Netflix/Hystrix)
* [MYSQL](https://www.mysql.com/)
* [MONGODB](https://www.mongodb.com/)

  </br>
  </br>
  </br>

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started



DATABASE COUNT
MYSQL
1
MONGODB
6
MYSQL
We have only 1 mysql database. Here we have to create a database named "userserviceDB"

Please note MYSQL database URL is usually : jdbc:mysql://localhost:3306/DATABASE NAME

### Things to Remember

I disabled the security by permitting all from the security config from the test process;



When Security is enabled - 
You have to send a POST request to /login page
Content type would be Application/JSON


example format -
{
    "username" : "gg",
    "password" : "1234"
}



Response you will get a JSON.

example -

{
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZyIsInJvbGVzIjpbInVzZXIiXSwiaXNzIjoiSW5lZmZhYmxlIiwiZXhwIjoxNjQzMDI2NDYxfQ.96QEn1yaKtTagYXOUATirIkWxKk5eq5Z5NAXBjG1kJ4",
    
   "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZyIsImlzcyI6IkluZWZmYWJsZSIsImV4cCI6MTY0MzAzMjM0MX0.CUbCgnINMeapuS0suOqNGK1ftKWN6gN_WD5W_AIYiwA"
}





Its a JWT token. You can use this to access page based on your role.


To make a request to any secure page, Add a header.
add header key "Authorization" and value "Bearer access_token" without quotes.
example - 




![EXAMPLE AUTHORIZATION][exampleAuthorization]




### Prerequisites

To run this project you need -

* JAVA Version 15
* MYSQL
* MONGODB


### Installation

Here's our installion guide

1. Clone the repo
   ```sh
   git clone https://github.com/your_username_/Project-Name.git
   ```
2. Open it in an IDE(Intellij Idea preffered)
   `
3. Let maven do it's things

4. See the documentations

[Documentation Site](https://ineffablekenobi.github.io/Shopfast2.0/)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Add User Service and Add JWT
- [x] Build a config server
- [x] Build config clients
- [x] Try dynamic config push
- [x] Build Product Service
- [x] Add shopCode to Product and User
- [x] Add an Authentication Service with token in user
- [x] Build a Inventory Service 
- [x] Build a Shop Service
- [x] Build Eureka server
- [x] Make existing Apis Eureka clients
- [x] Improve the Service function by inter api info transfer
  - [x] Inventory Service Optimization
  - [x] Product Service Opitmization 
  - [x] User Service Optimization
- [ ] Build a dependency model
- [x] Add validation
  - [x] Product Service
  - [x] Inventory Service
  - [x] Shop Service 
- [x] Build Shipping Service
- [x] Build Order Service
- [x] Build an API Gateway(Eureka Based)
- [ ] Test Authentication on the gateway
- [ ] Develop hierarchy based authentication technique
- [ ] Fault tolerance and isolation


<p align="right">(<a href="#top">back to top</a>)</p>





<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Your Name - [@your_twitter](https://twitter.com/your_username) - email@example.com

Project Link: [https://github.com/your_username/repo_name](https://github.com/your_username/repo_name)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/ineffablekenobi/Shopfast2.0/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
[exampleAuthorization]: images/RequestExample.JPG
