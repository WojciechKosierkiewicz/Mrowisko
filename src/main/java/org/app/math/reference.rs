pub struct Matrix {
    pub data: Vec<Vec<f64>>,
}

impl Matrix {
    pub fn new(data: Vec<Vec<f64>>) -> Matrix {
        Matrix { data }
    }

    pub fn round(&self) -> Matrix {
        let mut result = vec![vec![0.0; self.data[0].len()]; self.data.len()];
        for i in 0..self.data.len() {
            for j in 0..self.data[0].len() {
                result[i][j] = self.data[i][j].round();
            }
        }
        Matrix::new(result)
    }

    pub fn transmutate(&self) -> Matrix {
        let mut result = vec![vec![0.0; self.data.len()]; self.data[0].len()];
        for i in 0..self.data.len() {
            for j in 0..self.data[0].len() {
                result[j][i] = self.data[i][j];
            }
        }
        Matrix::new(result)
    }

    pub fn multiply(&self, other: &Matrix) -> Matrix {
        let mut result = vec![vec![0.0; other.data[0].len()]; self.data.len()];
        for i in 0..self.data.len() {
            for j in 0..other.data[0].len() {
                for k in 0..other.data.len() {
                    result[i][j] += self.data[i][k] * other.data[k][j];
                }
            }
        }
        Matrix::new(result)
    }

    pub fn rotation_matrix_x(angle: f64) -> Matrix {
        let mut result = vec![vec![0.0; 3]; 3];
        result[0] = vec![1.0, 0.0, 0.0];
        result[1] = vec![0.0, angle.cos(), -angle.sin()];
        result[2] = vec![0.0, angle.sin(), angle.cos()];
        Matrix::new(result)
    }

    pub fn rotation_matrix_y(angle: f64) -> Matrix {
        let mut result = vec![vec![0.0; 3]; 3];
        result[0] = vec![angle.cos(), 0.0, angle.sin()];
        result[1] = vec![0.0, 1.0, 0.0];
        result[2] = vec![-angle.sin(), 0.0, angle.cos()];
        Matrix::new(result)
    }

    pub fn rotation_matrix_z(angle: f64) -> Matrix {
        let mut result = vec![vec![0.0; 3]; 3];
        result[0] = vec![angle.cos(), -angle.sin(), 0.0];
        result[1] = vec![angle.sin(), angle.cos(), 0.0];
        result[2] = vec![0.0, 0.0, 1.0];
        Matrix::new(result)
    }

    pub fn general_rotation_matrix(x: f64, y: f64, z: f64) -> Matrix {
        let mut result = vec![vec![0.0; 3]; 3];
        result[0] = vec![
            y.cos() * z.cos(),
            z.cos() * x.sin() * y.sin() - x.cos() * z.sin(),
            x.cos() * z.cos() * y.sin() + x.sin() * z.sin(),
        ];
        result[1] = vec![
            y.cos() * z.sin(),
            x.cos() * z.cos() + x.sin() * y.sin() * z.sin(),
            -z.cos() * x.sin() + x.cos() * y.sin() * z.sin(),
        ];
        result[2] = vec![-y.sin(), x.sin() * y.cos(), x.cos() * y.cos()];
        Matrix::new(result)
    }
}

pub struct Point {
    pub x: f64,
    pub y: f64,
    pub z: f64,
}

impl Point {
    pub fn new(x: f64, y: f64, z: f64) -> Point {
        Point { x: x, y: y, z: z }
    }

    fn as_matrix(&self) -> Matrix {
        Matrix::new(vec![vec![self.x], vec![self.y], vec![self.z]])
    }

    fn matrix_to_point(m: Matrix) -> Point {
        Point::new(m.data[0][0], m.data[1][0], m.data[2][0])
    }

    pub fn rotate(&self, x: f64, y: f64, z: f64) -> Point {
        let p = self.as_matrix();
        let rotation = Matrix::general_rotation_matrix(x, y, z);
        let rotated = p.multiply(&rotation);
        Point::matrix_to_point(rotated)
    }
}

pub struct Display {
    width: u32,
    height: u32,
    brush: Vec<char>,
}

impl Display {
    pub fn new(width: u32, height: u32, brush: String) -> Display {
        let brush = brush.chars().collect();
        Display {
            width,
            height,
            brush,
        }
    }

    pub fn draw_from_point_array(&self, points: Vec<Point>) {
        let mut buffer = vec![vec![0.0; self.width as usize]; self.height as usize];
        for point in points {
            let x = point.x as usize;
            let y = point.y as usize;
            if x < self.width as usize && y < self.height as usize {
                buffer[y][x] = 1.0;
            }
        }
        for row in buffer {
            for pixel in row {
                if pixel == 1.0 {
                    print!("*");
                } else {
                    print!(" ");
                }
            }
            println!();
        }
    }
}
