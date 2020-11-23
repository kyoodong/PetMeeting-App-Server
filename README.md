# DB 구성
<pre><code>URL : https://aquerytool.com:443/aquerymain/index/?rurl=5f4c1eda-3ffe-4c6a-b523-f45c80705404&
Password : 064oct
</code></pre>

# REST API
## User
### 회원가입  
url : **/users**  
method : POST  
<pre><code>테스트 코드  
curl --location --request POST '127.0.0.1:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "test1",
    "email": "email1"
}'</code></pre>

### 조회  
url : **/users/{id}**  
method : GET  
<pre><code>테스트 코드  
curl --location --request GET '127.0.0.1:8080/users/test1'</code></pre>

### 성별 설정  
url : **/users/{id}**  
method : PUT  
<pre><code>테스트 코드  
curl --location --request PUT '127.0.0.1:8080/users/test1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "test1",
    "gender": "F"
}'</code></pre>

### 프로필 사진 설정  
url : **/users/{id}/profile-image**  
method : POST  
<pre><code>테스트 코드  
curl --location --request POST '127.0.0.1:8080/users/test1/profile-image' \
--form 'image=@{절대경로}/{파일이름}.jpg'</code></pre>
<pre><code>예) curl --location --request POST '127.0.0.1:8080/users/test1/profile-image' \
--form 'image=@/Users/dongkyoo/images/62374877_131796688052062_9209740804423327880_n.jpg'</code></pre>
