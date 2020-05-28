import React from 'react';
import axios from 'axios';
import ReactDOM from 'react-dom';
import {configure} from 'axios-hooks';
import App from './App';
import {getCookie} from 'hooks/useAuthenticationService';
import {ACCESS_TOKEN} from 'consts/cookies';

axios.defaults.headers.common['Authorization'] = getCookie(ACCESS_TOKEN);

ReactDOM.render(<App />, document.getElementById('root'));
