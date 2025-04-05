import * as React from 'react';
import { useState } from 'react';
import { TextField, Container, Paper, Box, Button, ThemeProvider } from '@mui/material';
import { createTheme, alpha, getContrastRatio } from '@mui/material/styles';

export default function Student() {
  const paperStyle = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [students, setStudents] = useState([]);

  const redBase = '#d32f2f'
  const redMain = alpha(redBase, 0.7);
  const theme = createTheme({
    palette: {
      red: {
        main: redMain,
        light: alpha(redBase, 0.5),
        dark: alpha(redBase, 0.9),
        contrastText: getContrastRatio(redMain, '#fff') > 4.5 ? '#fff' : '#111',
      },
    },
  });
  
  

  return (
    <ThemeProvider theme={theme}>
    <Container>
      <Paper elevation={3} style={paperStyle}>
          <h1 style={{color:"LightSlateGrey"}}><u>Add Student:</u></h1>
        <Box
          component="form"
          sx={{
            '& > :not(style)': { m: 1 },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField
            id="student-name"
            label="Name" 
            variant="outlined"
            fullWidth
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <TextField
            id="student-email"
            label="Email"
            variant="outlined"
            fullWidth
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <Button variant="contained" color="red">
  Submit 
</Button>
        </Box>


        {name}
        {'\n'}
        {email}
      </Paper>
    </Container>
    </ThemeProvider>
  );
}
