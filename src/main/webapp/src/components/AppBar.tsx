import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import {AppBar, Button, Toolbar, Typography, IconButton} from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';

import type {FunctionComponent} from 'react';

const useStyles = makeStyles((theme) => ({
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
}));

export type AppBarProps = {
  logout: () => void,
}

const AppBarComponent: FunctionComponent<AppBarProps> = ({logout}) => {
  const classes = useStyles();
  return (
    <AppBar position="static">
      <Toolbar>
        <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
          <MenuIcon />
        </IconButton>
        <Typography variant="h6" className={classes.title}>
          Careless
        </Typography>
        <Button color="inherit" onClick={() => logout()}>Logout</Button>
      </Toolbar>
    </AppBar>
  );
}

export default AppBarComponent;