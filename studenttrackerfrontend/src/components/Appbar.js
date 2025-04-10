import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import Button from '@mui/material/Button'
import { useNavigate } from 'react-router-dom';


export default function Appbar( {isAdminLoggedIn, setIsAdminLoggedIn }) {
  const navigate = useNavigate();

  const handleClickLogin = () => {
    navigate('/login');
  };

  const handleClickHome = () => {
    navigate('/')
  }

  const handleClickLogout = () => {
    setIsAdminLoggedIn(false);
    navigate('/');
  }

  

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          
  
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Student Tracker
          </Typography>

          {isAdminLoggedIn ? (
            <>
              <Typography sx={{ marginRight: 2 }}>ADMIN MODE</Typography>
              <Button color="inherit" onClick={handleClickLogout}>Logout</Button>
            </>
          ) : (
            <>
            <Button color="inherit" onClick={handleClickHome}>Home</Button>
            <Button color="inherit" onClick={handleClickLogin}>Admin Login</Button>
            </>
          )}

        </Toolbar>
      </AppBar>
    </Box>
  );
}
