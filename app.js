const express = require('express');
const bodyParser = require('body-parser');
const axios = require('axios');

const app = express();
const PORT = 3000;

// 뷰 엔진 설정
app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');

// 미들웨어 설정
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static('public'));

// 카카오톡 대화하기 화면을 렌더링하는 라우트
app.get('/', (req, res) => {
    res.render('index');
});

// 카카오 메시지 전송 요청 처리
app.post('/send-message', async (req, res) => {
    const { message, accessToken } = req.body;

    try {
        const kakaoApiUrl = 'https://kapi.kakao.com/v2/api/talk/memo/default/send';
        const headers = {
            'Authorization': `Bearer ${accessToken}`,
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        };

        const templateObject = {
            object_type: 'text',
            text: message,
            link: {
                web_url: 'http://yourwebsite.com'
            }
        };

        const params = new URLSearchParams();
        params.append('template_object', JSON.stringify(templateObject));

        // 카카오 메시지 전송 요청
        const response = await axios.post(kakaoApiUrl, params, { headers });
        if (response.status === 200) {
            res.send('Message sent successfully!');
        } else {
            res.send('Failed to send message.');
        }
    } catch (error) {
        console.error('Error:', error);
        res.send('An error occurred while sending the message.');
    }
});

// 서버 실행
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});