import React from 'react';
import axios from 'axios';
import useAxios from 'axios-hooks';
import {useCookies} from 'react-cookie';
import {makeStyles} from '@material-ui/core/styles';
import {Grid, List, ListItem, ListItemAvatar, Avatar, ListItemText} from '@material-ui/core';
import PersonIcon from '@material-ui/icons/Person';
import MenuIcon from '@material-ui/icons/Menu';
import {LOGIN_ROUTE, USER_LIST_API, MESSAGE_API} from 'consts/routes';
import {ACCESS_TOKEN, USER_NAME} from 'consts/cookies';
import useAuthenticationService from 'hooks/useAuthenticationService';
import AppBar from 'components/AppBar';
import Conversation from 'components/Conversation';

import type {User} from 'types/User';
import type {FunctionComponent} from 'react';
import type {Conversation as ConversationType} from 'types/Conversation';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  mainContainer: {
    height: '100%',
  },
  userList: {
    backgroundColor: theme.palette.background.paper,
  }
}));

const ConversationsView: FunctionComponent<{}> = () => {
  const classes = useStyles();
  const {logout} = useAuthenticationService();
  const [{data: users = []}] = useAxios<User[]>(USER_LIST_API);


  const onSubmit = async (message: string): Promise<void> => {
    console.log(message);
    await axios.post(MESSAGE_API("12412515"), message);
  }

  const conversation: ConversationType = {
    id: "1m",
    participants: [
      {
        username: "potato",
        id: "potato",
      },
      {
        username: "pedro",
        id: "pedro"
      }
    ],
    messages: [
      {
        id: "message1",
        text: "hello world",
        sender: {
          username: "pedro",
          id: "pedro"
        },
        date: new Date(),
        readers: [
          {
            username: "pedro",
            id: "pedro"
          },
        ]
      },
       {
        id: "message1",
        text: "hello Pedro",
        sender: {
          username: "potato",
          id: "potato"
        },
        date: new Date(),
        readers: [
          {
            username: "pedro",
            id: "pedro"
          },
          {
            username: "potato",
            id: "potato"
          },
        ]
      }
    ],
  }

  const user: User = {
    username: "pedro",
    id: "pedro"
  }

  return (
    <>
      <AppBar logout={logout} />
      <Grid container className={classes.mainContainer}>
        <Grid item xs={3} lg={2}>
          <List className={classes.root}>
            {users.map(({id, username}) => (
              <ListItem>
                <ListItemAvatar>
                  <Avatar>
                    <PersonIcon />
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary={username} />
              </ListItem>
            ))}
          </List>
        </Grid>
        <Grid item xs={9} lg={10}>
          <Conversation {...conversation} user={user} onSubmit={onSubmit} />
        </Grid>
      </Grid>
    </>
  );
}

export default ConversationsView;