import * as React from 'react';
import { useState } from 'react';
import {
  TextField,
  Container,
  Paper,
  Box,
  Button,
  ThemeProvider,
  Typography,
} from '@mui/material';
import { createTheme, alpha, getContrastRatio } from '@mui/material/styles';

export default function Student() {
  const paperStyle = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [students, setStudents] = useState([]);

  const handleClickSubmit = (e) => {
    e.preventDefault();
    const student = { name, email };
    fetch('http://localhost:8080/student/add', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(student),
    }).then(() => {
      setName('');
      setEmail('');
      fetch('http://localhost:8080/student/getall')
        .then((response) => response.json())
        .then((result) => setStudents(result));
    });
  };

  React.useEffect(() => {
    fetch('http://localhost:8080/student/getall')
      .then((response) => response.json())
      .then((result) => {
        setStudents(result);
      })
      .catch((error) => {
        console.error('Error fetching student data', error);
      });
  }, []);

  const redBase = '#d32f2f';
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
        <Paper elevation={4} style={paperStyle}>
          <h1 style={{ color: 'LightSlateGrey' }}>
            <u>Add Student:</u>
          </h1>
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
            <Button variant="contained" color="red" onClick={handleClickSubmit}>
              Submit
            </Button>
          </Box>
        </Paper>
        <Box display="flex" justifyContent="center" alignItems="center">
          <Typography variant="h4">Students</Typography>
        </Box>
        <Paper elevation={3} style={paperStyle}>
          {students.length === 0 ? (
            <p>No Student data found</p>
          ) : (
            students.map((student) => (
              <Paper
                elevation={6}
                style={{ margin: '10px', padding: '15px', textAlign: 'left' }}
                key={student.id}
              >
                ID: {student.id}
                <br />
                Name: {student.name}
                <br />
                Email Address: {student.email}
                <br />
              </Paper>
            ))
          )}
        </Paper>
      </Container>
    </ThemeProvider>
  );
}
