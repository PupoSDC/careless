import React from 'react';
import {useCookies} from 'react-cookie';
import {BrowserRouter, Redirect, Route, Switch} from 'react-router-dom';
import {CssBaseline} from '@material-ui/core';
import {ThemeProvider} from '@material-ui/core/styles';
import myFilesMuiTheme from 'utils/myFilesMuiTheme';
import {LOGIN_ROUTE, CONVERSATIONS_ROUTE} from 'consts/routes';
import {ACCESS_TOKEN, USER_NAME} from 'consts/cookies';
import useAuthenticationService from 'hooks/useAuthenticationService';
import ConversationsView from './views/ConversationsView';
import LoginView from './views/LoginView';


import type {FunctionComponent} from 'react';

const App: FunctionComponent<{}> = () => {
  const {user} = useAuthenticationService();
  return (
    <React.StrictMode>
      <CssBaseline>
        <ThemeProvider theme={myFilesMuiTheme}>
          <BrowserRouter>
            <Redirect path="/" to={user ? CONVERSATIONS_ROUTE : LOGIN_ROUTE} />
            <Switch>
              <Route path={LOGIN_ROUTE} component={LoginView} />
              <Route path={CONVERSATIONS_ROUTE} component={ConversationsView} />
            </Switch>
          </BrowserRouter>
        </ThemeProvider>
      </CssBaseline>
    </React.StrictMode>
  );
}

export default App;
