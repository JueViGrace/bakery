package types

import (
	"strings"
	"time"

	"github.com/JueViGrace/bakery-go/internal/database"
	"github.com/google/uuid"
)

type ProductResponse struct {
	ID          uuid.UUID `json:"id"`
	Name        string    `json:"name"`
	Description string    `json:"description"`
	Category    string    `json:"category"`
	Price       float64   `json:"price"`
	Stock       int       `json:"stock"`
	Issued      int       `json:"issued"`
	HasStock    int       `json:"hasStock"`
	Discount    float64   `json:"discount"`
	Rating      float64   `json:"rating"`
	Images      []string  `json:"images"`
	CreatedAt   string    `json:"createdAt"`
	UpdatedAt   string    `json:"updatedAt"`
	DeletedAt   string    `json:"-"`
}

type CreateProductRequest struct {
	Name        string   `json:"name"`
	Description string   `json:"description"`
	Category    string   `json:"category"`
	Price       float64  `json:"price"`
	Stock       int      `json:"stock"`
	Issued      int      `json:"issued"`
	HasStock    int      `json:"hasStock"`
	Discount    float64  `json:"discount"`
	Rating      float64  `json:"rating"`
	Images      []string `json:"images"`
}

type UpdateProductRequest struct {
	Description string    `json:"description"`
	Category    string    `json:"category"`
	Price       float64   `json:"price"`
	Stock       int       `json:"stock"`
	Issued      int       `json:"issued"`
	HasStock    int       `json:"hasStock"`
	Discount    float64   `json:"discount"`
	Rating      float64   `json:"rating"`
	Images      []string  `json:"images"`
	ID          uuid.UUID `json:"id"`
}

func DbProductToProduct(db *database.BakeryProduct) *ProductResponse {
	id, err := uuid.Parse(db.ID)
	if err != nil {
		return nil
	}

	return &ProductResponse{
		ID:          id,
		Name:        db.Name,
		Description: db.Description,
		Category:    db.Category,
		Price:       db.Price,
		Stock:       int(db.Stock),
		Issued:      int(db.Issued),
		HasStock:    int(db.HasStock),
		Discount:    db.Discount,
		Rating:      db.Rating,
		Images:      strings.Split(db.Images, ","),
		CreatedAt:   db.CreatedAt,
		UpdatedAt:   db.UpdatedAt,
		DeletedAt:   db.DeletedAt.String,
	}
}

func NewCreateProductParams(r *CreateProductRequest) *database.CreateProductParams {
	id, err := uuid.NewV7()
	if err != nil {
		return nil
	}

	return &database.CreateProductParams{
		ID:          id.String(),
		Name:        r.Name,
		Description: r.Description,
		Category:    r.Category,
		Price:       r.Price,
		Stock:       int64(r.Stock),
		Issued:      int64(r.Issued),
		HasStock:    int64(r.HasStock),
		Discount:    r.Discount,
		Rating:      r.Discount,
		Images:      strings.Join(r.Images, ","),
		CreatedAt:   time.Now().UTC().String(),
		UpdatedAt:   time.Now().UTC().String(),
	}
}

func NewUpdateProductParams(r *UpdateProductRequest) *database.UpdateProductParams {
	return &database.UpdateProductParams{
		Description: r.Description,
		Category:    r.Category,
		Price:       r.Price,
		Stock:       int64(r.Stock),
		Issued:      int64(r.Issued),
		HasStock:    int64(r.HasStock),
		Discount:    r.Discount,
		Rating:      r.Rating,
		Images:      strings.Join(r.Images, ","),
		UpdatedAt:   time.Now().UTC().String(),
		ID:          r.ID.String(),
	}
}
