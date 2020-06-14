import React from 'react';
import axios from 'axios';
import ReactDOM from 'react-dom';
import {configure} from 'axios-hooks';
import App from './App';
import {getCookie} from 'hooks/useAuthenticationService';
import {ACCESS_TOKEN} from 'consts/cookies';

import {Client} from '@stomp/stompjs';

axios.defaults.headers.common['Authorization'] = getCookie(ACCESS_TOKEN);

// ReactDOM.render(<App />, document.getElementById('root'));


const client = new Client({
  brokerURL: "ws://localhost:8080/ws?access_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraXdpIiwiaWF0IjoxNTkyMDc5NzkzLCJleHAiOjE1OTIxNjYxOTN9.b6400d01ivCvCPnS8uZpgOor9e1xY0rdt7ODDTwC8QFhyeL5qyEYSyjWTsPZOmHXDeW0FOu9idi1VynNBYZFyw",
  debug: (str: string) => console.log(str),
})

client.onConnect = () => console.log("COnnected!");
client.activate();

setTimeout(() => {
  client.publish({destination: '/conversation/sendMessage', body: 'hello world'});
}, 10000)
