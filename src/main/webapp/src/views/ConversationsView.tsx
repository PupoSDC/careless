import React from 'react';
import axios from 'axios';
import useAxios from 'axios-hooks';
import {useCookies} from 'react-cookie';
import {makeStyles} from '@material-ui/core/styles';
import {Grid, List, ListItem, ListItemAvatar, Avatar, ListItemText, Divider} from '@material-ui/core';
import PersonIcon from '@material-ui/icons/Person';
import MenuIcon from '@material-ui/icons/Menu';
import {LOGIN_ROUTE, USER_LIST_API, MESSAGE_API, CONVERSATIONS_API} from 'consts/routes';
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
  const [{data: conversations = []}] = useAxios<ConversationType[]>(CONVERSATIONS_API);
  const [{data: conversation}] = useAxios<ConversationType>(MESSAGE_API("5ee5359951a9e95eb3fafc38"));

  const onSubmit = async (message: string): Promise<void> => {
    await axios.post(MESSAGE_API("5ee5359951a9e95eb3fafc38"), {text: message});
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
            <Divider />
            {conversations.map(({id, name}) => (
              <ListItem>
                <ListItemAvatar>
                  <Avatar>
                    <PersonIcon />
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary={name} />
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