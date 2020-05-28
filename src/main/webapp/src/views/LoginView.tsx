import React, {useState} from 'react';
import {Avatar, Button, CssBaseline, TextField, FormControlLabel} from '@material-ui/core';
import {Checkbox, Typography, Container} from '@material-ui/core';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import {makeStyles} from '@material-ui/core/styles';
import useAuthenticationService from 'hooks/useAuthenticationService';

import type {FunctionComponent} from 'react';
import type {JwtResponse} from 'types/JwtResponse';

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 0),
  },
}));

/**
 * Sourced from the template from
 * https://github.com/mui-org/material-ui/blob/master/docs/src/pages/getting-started/templates/sign-in/SignIn.js
 */
const LoginView: FunctionComponent<{}> = () => {
  const classes = useStyles();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const {login, register, loading, error} = useAuthenticationService();

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
        <form
          noValidate
          className={classes.form}
          onSubmit={(event) => {
            event.preventDefault();
            login({username, password})
          }}
         >
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="username"
            label="Username"
            name="username"
            value={username}
            onChange={(event) => {setUsername(event.target.value)}}
            autoFocus
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            value={password}
            onChange={(event) => {setPassword(event.target.value)}}
            autoComplete="current-password"
          />
          { error && <Typography color={"error"} >{error}</Typography>}
          <FormControlLabel
            control={<Checkbox value="remember" color="primary" />}
            label="Remember me"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign In
          </Button>
          <Button
            type="button"
            fullWidth
            variant="contained"
            color="secondary"
            className={classes.submit}
            onClick={() => register({username, password})}
          >
            Register
          </Button>
        </form>
      </div>
    </Container>
  );
}

export default LoginView;