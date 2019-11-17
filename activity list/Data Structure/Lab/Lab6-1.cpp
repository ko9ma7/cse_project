#include <cstdio>
#include <cstdlib>

int** alloc2DInt1 (int rows, int cols) {
	if (rows <= 0 || cols <= 0) return NULL;

	int** mat = new int* [rows];
	for (int i = 0; i < rows; i++) mat[i] = new int[cols];

	return mat;
}

void free2DInt1 (int** mat, int rows, int cols = 0) {
	if (mat != NULL) {
		for (int i = 0; i < rows; i++) delete[] mat[i]; // cols의 값을 모두 해제

		delete[] mat; // rows의 값을 모두 해제
	}
}
//////////////////////////////////////////////////////////////////////////////////////////
int** alloc2DInt2 (int rows, int cols) {
    int** mat = new int*[rows];
    mat[0] = new int[rows*cols];

    for (int i = 1; i < rows; i++)
        mat[i] = mat[i - 1] + cols;

    return mat;
}

void free2DInt2 (int** mat, int rows) {
    if(mat != NULL) {
        delete[] mat[0];
        delete[] mat;
    }
}
//////////////////////////////////////////////////////////////////////////////////////////
int** alloc2DInt3 (int rows, int cols) {
    int size = sizeof(int*) * rows + sizeof(int) * rows * cols;
    char* buf = new char[size];

    int** mat = (int**)buf;
    mat[0] = (int*)(buf + sizeof(int*) * rows);

    for (int i = 1; i < rows; i++)
        mat[i] = mat[i - 1] + cols;

    return mat;
}

void free2DInt3 (int** mat) {
    delete[] mat;
}
//////////////////////////////////////////////////////////////////////////////////////////
void set2DRandom(int** mat, int rows, int cols) {
	for (int i = 0; i < rows; i++) for (int j = 0; j < cols; j++) mat[i][j] = rand() % 100;
}

void print2DInt(int** mat, int rows, int cols) {
	printf("행의 수 = %d, 열의 수 = %d\n", rows, cols);
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) printf("%4d", mat[i][j]);
		printf("\n");
	}
}

void main() {
	int** mat;
	int rows, cols;

	printf("행과 열의 크기를 입력하시오: "); scanf("%d%d", &rows, &cols);

	mat = alloc2DInt1(rows, cols);
	set2DRandom(mat, rows, cols);
	print2DInt(mat, rows, cols);
	free2DInt1(mat, rows);
}