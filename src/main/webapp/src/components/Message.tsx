import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import {Card, CardContent, Typography} from '@material-ui/core';

import type {FunctionComponent} from 'react';
import type {User} from 'types/User';
import type {Message as MessageType} from 'types/Conversation';

const useStyles = makeStyles({
  root: {
    minWidth: 275,
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
})

const Message: FunctionComponent<MessageType & {user: User}> =
  ({text, sender, date, readers, user}) => {
    const classes = useStyles();
    return (
      <Card className={classes.root}>
        <CardContent>
          <Typography variant="body2" component="p">{text}</Typography>
          <Typography variant="body2" component="p">{sender.username}</Typography>
        </CardContent>
      </Card>
    );
  }

export default Message;