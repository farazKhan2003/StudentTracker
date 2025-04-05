import * as React from 'react';
import { useState } from 'react';
import { TextField, Container, Paper, Box } from '@mui/material';

export default function Student() {
  const paperStyle = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [students, setStudents] = useState([]);

  return (
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
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
        </Box>
      </Paper>
    </Container>
  );
}
