import {useState} from 'react';
import axios from 'axios';
import {useCookies} from 'react-cookie';
import {ACCESS_TOKEN, USER_NAME} from 'consts/cookies';
import {LOGIN_API, REGISTER_API} from 'consts/routes';

import type {UserCredentials} from 'types/UserCredentials';
import type {JwtResponse} from 'types/JwtResponse';

type AuthenticationService = {
  login:  (user: UserCredentials) => Promise<void>,
  logout: () => void,
  register: (user: UserCredentials) => Promise<void>,
  user: string | null,
  loading: boolean,
  error: Error | null,
}

export default (): AuthenticationService => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<Error | null>(null);
  const [cookies, setCookie, removeCookie] = useCookies([ACCESS_TOKEN, USER_NAME]);
  const accessToken = cookies[ACCESS_TOKEN];
  const user = cookies[USER_NAME];

  axios.defaults.headers.common['Authorization'] = accessToken ?`Bearer ${accessToken}` : '';

  const makeAuthorizationRequest =
    async (apiEndpoint: string, user: UserCredentials): Promise<void> => {
      setLoading(true);
      try {
        const {data: response} = await axios.post<JwtResponse>(apiEndpoint, user);
        await setCookie(ACCESS_TOKEN, response.token);
        await setCookie(USER_NAME, response.username);
        // document.location.href = '/';
      } catch (error) {
        setError(error.toString());
      }
      setLoading(false);
    }

  const logout = () => {
    setLoading(true);
    try {
      removeCookie(ACCESS_TOKEN);
      removeCookie(USER_NAME);
      document.location.href = '/';
    } catch (error) {
      setError(error.toString());
    }
    setLoading(false);
  }

  return {
    login: (user: UserCredentials) => makeAuthorizationRequest(LOGIN_API, user),
    register: (user: UserCredentials) => makeAuthorizationRequest(REGISTER_API, user),
    user: (accessToken && user) ? user : null,
    logout,
    loading,
    error,
  }
}

/** Not hooked yet and need the cookies? use this old school function **/
export const getCookie = (name: string): string | undefined => {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts && parts.length === 2) {
    return parts.pop()?.split(';').shift();
  }
}
