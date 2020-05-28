import React, {useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import {TextField} from '@material-ui/core';
import Message from 'components/Message';

import type {FunctionComponent, KeyboardEvent} from 'react';
import type {User} from 'types/User';
import type {Conversation as ConversationType} from 'types/Conversation';

const useStyles = makeStyles((theme) => ({
  root: {
    backgroundColor: theme.palette.background.paper,
    height: '100%',
    width: '100%',
    display: 'flex',
    flexDirection: 'column',
  },
  messageContainer: {
    flexGrow: 1,
    display: 'flex',
    flexDirection: 'column-reverse',
    'overflow-y': "auto",
  }
}));

export type ConversationProps = ConversationType & {
  user: User,
  onSubmit: (message: string) => Promise<void>,
};

const Conversation: FunctionComponent<ConversationProps> =
  ({participants, messages = [], user, onSubmit}) => {
    const classes = useStyles();
    const [message, setMessage] = useState("");

   const onKeyDown = async (event: KeyboardEvent<HTMLInputElement>): Promise<void> => {
      // 'keypress' event misbehaves on mobile so we track 'Enter' key via 'keydown' event
      if (event.key === 'Enter') {
        event.preventDefault();
        event.stopPropagation();
        await onSubmit(message);
        setMessage("");
      }
    }

    return (
      <div className={classes.root}>
        <div className={classes.messageContainer}>
          {messages.map((message) => <Message {...message} user={user} />)}
        </div>
        <TextField
          id="outlined-multiline-static"
          multiline
          rows={4}
          variant="outlined"
          value={message}
          onChange={(event) => setMessage(event.target.value)}
          onKeyDown={onKeyDown}
        />
      </div>
    );
  }

export default Conversation;